package simplecalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.Serializable;

public class Mousepoint implements ActionListener, Serializable {
    private static JLabel clickCountLabel;
    private static JTextField clickCountField;
    private static JLabel delayLabel;
    private static JTextField delayField;
    private static JButton startButton;
    private static JFrame frame;
    private static JPanel panel;

    public static void main(String[] args) {
        // 设置 JFrame 和 JPanel
        frame = new JFrame("Mouse Clicker");
        panel = new JPanel();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setLayout(null);

        // 添加 Click Count Label 和 TextField
        clickCountLabel = new JLabel("Click Count:");
        clickCountLabel.setBounds(10, 20, 80, 25);
        panel.add(clickCountLabel);

        clickCountField = new JTextField();
        clickCountField.setBounds(100, 20, 165, 25);
        panel.add(clickCountField);

        // 添加 Delay Label 和 TextField
        delayLabel = new JLabel("Delay (ms):");
        delayLabel.setBounds(10, 50, 80, 25);
        panel.add(delayLabel);

        delayField = new JTextField();
        delayField.setBounds(100, 50, 165, 25);
        panel.add(delayField);

        // 添加 Start Clicking 按钮
        startButton = new JButton("Start Clicking");
        startButton.setBounds(10, 80, 300, 50);
        panel.add(startButton);
        startButton.addActionListener(new Mousepoint());

        // 显示 JFrame
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int clickCount = Integer.parseInt(clickCountField.getText());
            int delay = Integer.parseInt(delayField.getText());
            startClicking(clickCount, delay);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers.");
        }
    }

    private void startClicking(int clickCount, int delay) {
        try {
            Robot robot = new Robot();
            for (int i = 0; i < clickCount; i++) {
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                System.out.println("Clicked " + (i + 1) + " times");
                robot.delay(delay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
