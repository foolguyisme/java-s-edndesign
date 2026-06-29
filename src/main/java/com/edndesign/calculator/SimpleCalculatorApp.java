package com.edndesign.calculator;

import javax.swing.*;
import java.awt.*;

/**
 * Swing-based desktop calculator with basic arithmetic operations.
 */
public class SimpleCalculatorApp extends JFrame {

    private final JTextField displayField = new JTextField();

    public SimpleCalculatorApp() {
        configureWindow();
        add(buildDisplay(), BorderLayout.NORTH);
        add(buildButtonPanel(), BorderLayout.CENTER);
    }

    private void configureWindow() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JTextField buildDisplay() {
        displayField.setEditable(false);
        displayField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        return displayField;
    }

    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 4));
        String[] labels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String label : labels) {
            JButton button = new JButton(label);
            button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
            applyButtonStyle(button, label);
            button.addActionListener(event -> handleButtonPress(label));
            panel.add(button);
        }
        return panel;
    }

    private void applyButtonStyle(JButton button, String label) {
        if ("C".equals(label)) {
            button.setBackground(Color.RED);
            button.setForeground(Color.WHITE);
        } else if ("=".equals(label)) {
            button.setBackground(new Color(46, 125, 50));
            button.setForeground(Color.WHITE);
        } else if ("+-*/".contains(label)) {
            button.setBackground(new Color(25, 118, 210));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(Color.LIGHT_GRAY);
        }
    }

    private void handleButtonPress(String label) {
        if ("C".equals(label)) {
            displayField.setText("");
            return;
        }
        if ("=".equals(label)) {
            calculateResult();
            return;
        }
        displayField.setText(displayField.getText() + label);
    }

    private void calculateResult() {
        try {
            double result = ExpressionEvaluator.evaluate(displayField.getText());
            displayField.setText(String.valueOf(result));
        } catch (ArithmeticException ex) {
            displayField.setText("Error: division by zero");
        } catch (IllegalArgumentException ex) {
            displayField.setText("Error: invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleCalculatorApp app = new SimpleCalculatorApp();
            app.setVisible(true);
        });
    }
}
