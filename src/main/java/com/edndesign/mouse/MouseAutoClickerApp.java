package com.edndesign.mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

/**
 * Desktop utility that automates mouse clicks at a configurable interval.
 */
public class MouseAutoClickerApp extends JFrame implements ActionListener {

    private final JTextField clickCountField = new JTextField();
    private final JTextField delayField = new JTextField();

    public MouseAutoClickerApp() {
        setTitle("Mouse Auto Clicker");
        setSize(350, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(buildPanel());
    }

    private JPanel buildPanel() {
        JPanel panel = new JPanel(null);

        JLabel clickCountLabel = new JLabel("Click Count:");
        clickCountLabel.setBounds(10, 20, 80, 25);
        panel.add(clickCountLabel);

        clickCountField.setBounds(100, 20, 165, 25);
        panel.add(clickCountField);

        JLabel delayLabel = new JLabel("Delay (ms):");
        delayLabel.setBounds(10, 50, 80, 25);
        panel.add(delayLabel);

        delayField.setBounds(100, 50, 165, 25);
        panel.add(delayField);

        JButton startButton = new JButton("Start Clicking");
        startButton.setBounds(10, 80, 300, 50);
        startButton.addActionListener(this);
        panel.add(startButton);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            int clickCount = Integer.parseInt(clickCountField.getText().trim());
            int delayMs = Integer.parseInt(delayField.getText().trim());

            if (clickCount <= 0) {
                throw new NumberFormatException("Click count must be positive");
            }
            if (delayMs < 0) {
                throw new NumberFormatException("Delay must be non-negative");
            }

            performClicks(clickCount, delayMs);
            JOptionPane.showMessageDialog(this, "Completed " + clickCount + " clicks.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid positive numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performClicks(int clickCount, int delayMs) {
        try {
            Robot robot = new Robot();
            for (int index = 0; index < clickCount; index++) {
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                if (delayMs > 0) {
                    robot.delay(delayMs);
                }
            }
        } catch (AWTException ex) {
            JOptionPane.showMessageDialog(this, "Unable to control mouse: " + ex.getMessage(), "Robot Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MouseAutoClickerApp app = new MouseAutoClickerApp();
            app.setVisible(true);
        });
    }
}
