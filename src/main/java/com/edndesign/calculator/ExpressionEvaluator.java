package com.edndesign.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Evaluates arithmetic expressions with operator precedence (* / before + -).
 */
public final class ExpressionEvaluator {

    private ExpressionEvaluator() {
    }

    public static double evaluate(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException("Expression must not be empty");
        }

        String[] tokens = expression.split("(?=[-+*/])|(?<=[-+*/])");
        if (tokens.length == 0 || tokens.length % 2 == 0) {
            throw new IllegalArgumentException("Invalid expression format");
        }

        Deque<Double> numbers = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        double first = parseNumber(tokens[0]);
        numbers.push(first);

        for (int index = 1; index < tokens.length; index += 2) {
            char operator = tokens[index].charAt(0);
            double operand = parseNumber(tokens[index + 1]);

            if (operator == '*' || operator == '/') {
                double previous = numbers.pop();
                numbers.push(applyOperator(previous, operand, operator));
            } else {
                numbers.push(operand);
                operators.push(operator);
            }
        }

        double result = numbers.removeFirst();
        while (!operators.isEmpty()) {
            char operator = operators.removeFirst();
            double operand = numbers.removeFirst();
            result = applyOperator(result, operand, operator);
        }

        return result;
    }

    private static double applyOperator(double left, double right, char operator) {
        switch (operator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                if (right == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return left / right;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    private static double parseNumber(String token) {
        try {
            return Double.parseDouble(token.trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid number: " + token, ex);
        }
    }
}
