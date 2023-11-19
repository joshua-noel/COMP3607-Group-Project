package comp3607_group_project;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUIWindow {
    private JButton ProcessFolderbtn;
    private JButton ProcessMarkingpdfbtn;
    private JButton Reportbtn;
    private JButton Helpbtn;
    private JTextField textField;
    private JTextArea feedback;


    public GUIWindow(){
        initComponents();
    }

    public void initComponents(){
        // Create a new JFrame
        JFrame frame = new JFrame("Takes in the folder name to mark");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);

        // Create a new JPanel with a FlowLayout
        JPanel panel = new JPanel(new FlowLayout());

        // Create a label and text field for the folder name
        textField = new JTextField("Enter the name of the folder");
        textField.setPreferredSize(new Dimension(300, 20));

        // Create the three buttons
        ProcessFolderbtn = new JButton("Process Folder");
        ProcessFolderbtn.addActionListener(new ProcessFolderListener(this));

        ProcessMarkingpdfbtn = new JButton("Process the Marking PDF");
        ProcessMarkingpdfbtn.addActionListener(new ProcessMarkingpdfListener(this));

        Reportbtn = new JButton("Generate Report");
        Reportbtn.addActionListener(new GenerateReportListener(this));

        Helpbtn = new JButton("Help");

        // Add a JTextArea for feedback
        feedback = new JTextArea();
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
    public void processfolder(){
        ProcessFolderCommand processFolder = new ProcessFolderCommand(textField.getText());
        processFolder.execute();
        feedback.setText("Folder Processed");

    }
    public void processpdf(){
        ProcessPdfCommand processPdf = new ProcessPdfCommand(textField.getText());
        processPdf.execute();
        feedback.setText("");
        feedback.setText("");
    }
    public void processMarkingpdf(){
        ProcessPdfCommand processPdf = new ProcessPdfCommand(textField.getText());
        processPdf.execute();

        feedback.setText("");
        feedback.setText("PDF Processed");
    }
    public void generateReport(){
        ProcessAssignmentCommand processAssignment = new ProcessAssignmentCommand(textField.getText());
        processAssignment.execute();

        TestSuite tester = new TestSuite(textField.getText());
        try {
            tester.markAttributes();
            tester.markConstructors();
            tester.markMethods();

        } catch (Exception e) {
            feedback.setText("Error Generating Report");
            e.printStackTrace();
        }

        feedback.setText("");
        feedback.setText("Report Generated");
    }
}