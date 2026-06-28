import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MatthewSo extends JFrame {

    // Entering grades inputs
    private JTextField bioInput;
    private JTextField mathInput;
    private JTextField statInput;
    private JTextField engInput;

    // Other panel section with average output and calc button
    private JPanel mainPanel;
    private JButton calculateButton;
    private JLabel averageOutput;

    public MatthewSo() {

        // Main window setup
        setTitle("Layout Box Alignment");
        setSize(250, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create input boxes
        bioInput = new JTextField(12);
        mathInput = new JTextField(12);
        statInput = new JTextField(12);
        engInput = new JTextField(12);

        // Create output and calc button
        averageOutput = new JLabel("");
        averageOutput.setPreferredSize(new Dimension(80, 20));
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new MyActionListener());

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        add(mainPanel);

        createLayout();

        // Recreate layout whenever window is resized
        addComponentListener(new ResizeListener());
        setVisible(true);
    }

    // Whenever window is resized, recreate layout
    private class ResizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
            createLayout();
        }
    }

    // Depending on window width, create small, medium, or wide layout like instructions
    private void createLayout() {
        int width = getWidth();

        mainPanel.removeAll();

        if (width < 420) {
            createSmallLayout();
        } else if (width < 620) {
            createMediumLayout();
        } else {
            createWideLayout();
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Creates consistent labels with fixed
    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(45, 20));
        return label;
    }

    // Small layout: all panels are on top of each other
    private void createSmallLayout() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Creates subjects
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        row1.add(makeLabel("BIO:"));
        row1.add(bioInput);
        mainPanel.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        row2.add(makeLabel("MATH:"));
        row2.add(mathInput);
        mainPanel.add(row2);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        row3.add(makeLabel("STAT:"));
        row3.add(statInput);
        mainPanel.add(row3);

        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        row4.add(makeLabel("ENG:"));
        row4.add(engInput);
        mainPanel.add(row4);

        // Creates output and button
        JPanel row5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        row5.add(new JLabel("Average:"));
        row5.add(averageOutput);
        mainPanel.add(row5);

        JPanel row6 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        row6.add(calculateButton);
        mainPanel.add(row6);
    }

    // Medium layout: 2x2 of subjects with output and button below
    private void createMediumLayout() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Creates 2x2 grid of subjects
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        row1.add(makeLabel("BIO:"));
        row1.add(bioInput);
        row1.add(makeLabel("STAT:"));
        row1.add(statInput);
        mainPanel.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        row2.add(makeLabel("MATH:"));
        row2.add(mathInput);
        row2.add(makeLabel("ENG:"));
        row2.add(engInput);
        mainPanel.add(row2);

        // Creates output and button
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        row3.add(new JLabel("Average:"));
        row3.add(averageOutput);
        mainPanel.add(row3);

        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        row4.add(calculateButton);
        mainPanel.add(row4);
    }

    // Large layout: 2x2 of subjects with output and button to the right
    private void createWideLayout() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        // Create Left side; 2x2 grid of subjects
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
        row1.add(makeLabel("BIO:"));
        row1.add(bioInput);
        row1.add(makeLabel("STAT:"));
        row1.add(statInput);
        left.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
        row2.add(makeLabel("MATH:"));
        row2.add(mathInput);
        row2.add(makeLabel("ENG:"));
        row2.add(engInput);
        left.add(row2);

        // Create right side; output and button
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        row3.add(new JLabel("Average:"));
        row3.add(averageOutput);
        right.add(row3);

        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        row4.add(calculateButton);
        right.add(row4);

        mainPanel.add(left);
        mainPanel.add(Box.createHorizontalStrut(20));
        mainPanel.add(right);
    }

    // Calculates and outputs average grade
    private class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                // Reads grades from user and calculates average
                double bio = Double.parseDouble(bioInput.getText());
                double math = Double.parseDouble(mathInput.getText());
                double stat = Double.parseDouble(statInput.getText());
                double eng = Double.parseDouble(engInput.getText());

                double average = (bio + math + stat + eng) / 4.0;
                averageOutput.setText(String.format("%.4f", average));

            } catch (NumberFormatException ex) {

                // If try fails, its because user inputed invalid values
                JOptionPane.showMessageDialog(
                    null,
                    "Wrong input value!",
                    "CMSC 437 - Message",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public static void main(String[] args) {
        new MatthewSo();
    }
}