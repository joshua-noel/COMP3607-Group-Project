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
        Helpbtn.addActionListener(new HelpbtnListener(this));
        
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
    public void help(){
        feedback.setText("");
        feedback.setText("Step 1:\r\n" + //
                "An assignment zip file must be placed in the resources folder (src\\main\\resources). This zip file must contain one (1) PDF which is the lecturer/tutor marking rubric.\r\n" + //
                "\r\n" + //
                "Step 2:\r\n" + //
                "Enter the name of the desired assignment zip that you wish to mark and press 'Process Folder' and allow the program sometime to process the request.\r\n" + //
                "\r\n" + //
                "Step 3:\r\n" + //
                "Press 'Process the Marking PDF' and allow the program sometime to process the request.\r\n" + //
                "\r\n" + //
                "Step 4:\r\n" + //
                "Press 'Generate Report' and allow the program sometime to process the request. The report PDF will be located in (located in the project's main folder).\r\n" + //
                "\r\n" + //
                "NOTE:\r\n" + //
                "Some steps may throw errors due to the previous step not being completed, if a substantial amount of time has been given between steps and errors are still thrown, backtrack and try again.");
    }
    
}
