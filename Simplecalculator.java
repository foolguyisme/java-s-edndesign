package simplecalculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Stack;

public class Simplecalculator extends JFrame {
    private JTextField display;
    private Stack<Double> numbers;
    private Stack<Character> operators;

    public Simplecalculator() {
        numbers = new Stack<>();
        operators = new Stack<>();
        setupUI();
    }

    private void setupUI() {
        setTitle("基礎算盤?");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            
            if (text.equals("C")) {
                button.setBackground(Color.RED);
                button.setForeground(Color.WHITE);
            } else if (text.equals("=")) {
                button.setBackground(Color.GREEN);
                button.setForeground(Color.WHITE);
            } else if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")) {
                button.setBackground(Color.BLUE);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(Color.LIGHT_GRAY);
            }

            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) == 'C') {
                display.setText("");
                numbers.clear();
                operators.clear();
            } else if (command.charAt(0) == '=') {
                calculateResult();
            } else {
                display.setText(display.getText() + command);
            }
        }
    }

    private void calculateResult() {
        try {
            String input = display.getText();
            String[] tokens = input.split("(?=[-+*/])|(?<=[^-+*/][+*/])");
            double result = Double.parseDouble(tokens[0]);
            numbers.push(result);

            for (int i = 1; i < tokens.length; i += 2) {
                char operator = tokens[i].charAt(0);
                double num = Double.parseDouble(tokens[i + 1]);

                if (operator == '*' || operator == '/') {
                    double prevNum = numbers.pop();
                    if (operator == '*') {
                        numbers.push(prevNum * num);
                    } else {
                        if (num != 0) {
                            numbers.push(prevNum / num);
                        } else {
                            display.setText("錯誤: 不能除以零！");
                            return;
                        }
                    }
                } else {
                    numbers.push(num);
                    operators.push(operator);
                }
            }

            result = numbers.get(0);
            for (int i = 0; i < operators.size(); i++) {
                char operator = operators.get(i);
                double num = numbers.get(i + 1);
                if (operator == '+') {
                    result += num;
                } else if (operator == '-') {
                    result -= num;
                }
            }

            display.setText(String.valueOf(result));
            numbers.clear();
            operators.clear();
        } catch (Exception ex) {
            display.setText("錯誤: 輸入格式不正確！");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Simplecalculator calculator = new Simplecalculator();
            calculator.setVisible(true);
        });
    }
}
