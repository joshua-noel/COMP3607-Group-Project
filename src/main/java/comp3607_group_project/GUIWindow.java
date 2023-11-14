package comp3607_group_project;


import javax.swing.*;
import java.awt.*;

public class GUIWindow {
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Takes in the folder name to mark");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);

        // Create a new JPanel with a FlowLayout
        JPanel panel = new JPanel(new FlowLayout());

        // Create a label and text field for the folder name
        JTextField textField = new JTextField("Enter the name of the folder");
        textField.setPreferredSize(new Dimension(300, 20));

        // Create the three buttons
        JButton ProcessFolderbtn = new JButton("Process Folder");
        JButton ProcessMarkingpdfbtn = new JButton("Process the Marking PDF");
        JButton Reportbtn = new JButton("Generate Report");
        JButton Helpbtn = new JButton("Help");

        // Add a JTextArea for feedback
        JTextArea feedback = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(feedback);
        scrollPane.setPreferredSize(new Dimension(500, 100));

        // Add the text field to the panel
        panel.add(textField);

        // Add the buttons to the panel
        panel.add(ProcessFolderbtn);
        panel.add(ProcessMarkingpdfbtn);
        panel.add(Reportbtn);
        panel.add(Helpbtn);

        // Add the feedback area to the panel
        panel.add(scrollPane);

        // Add the panel to the frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // Display the window
        frame.setVisible(true);
    }
}