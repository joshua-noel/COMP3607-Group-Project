package comp3607_group_project;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
* Represents a GUI window for interacting with the application.
* Allows users to input folder names, process folders, marking PDFs, generate reports, and access help.
*/
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

    /**
     * Creates and configures the GUI components, including buttons, text fields, and feedback area.
     */
    public void initComponents(){
        JFrame frame = new JFrame("Takes in the folder name to mark");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);

        JPanel panel = new JPanel(new FlowLayout());

        textField = new JTextField("Enter the name of the folder");
        textField.setPreferredSize(new Dimension(300, 20));

        ProcessFolderbtn = new JButton("Process Folder");
        ProcessFolderbtn.addActionListener(new ProcessFolderListener(this));

        ProcessMarkingpdfbtn = new JButton("Process the Marking PDF");
        ProcessMarkingpdfbtn.addActionListener(new ProcessMarkingpdfListener(this));

        Reportbtn = new JButton("Generate Report");
        Reportbtn.addActionListener(new GenerateReportListener(this));

        Helpbtn = new JButton("Help");

        feedback = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(feedback);
        scrollPane.setPreferredSize(new Dimension(500, 100));

        panel.add(textField);

        panel.add(ProcessFolderbtn);
        panel.add(ProcessMarkingpdfbtn);
        panel.add(Reportbtn);
        panel.add(Helpbtn);

        panel.add(scrollPane);

        frame.getContentPane().add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    public void processfolder(){
        ProcessFolderCommand processFolder = new ProcessFolderCommand(textField.getText());
        processFolder.execute();
        feedback.setText("Folder Processed");

    }

    public void processMarkingpdf(){
        ProcessPdfCommand processPdf = new ProcessPdfCommand(textField.getText());
        processPdf.execute();

        feedback.setText("");
        feedback.setText("PDF Processed");
    }

    /**
     * Generates a report for the entered folder, executing the associated command and providing feedback.
     */
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
