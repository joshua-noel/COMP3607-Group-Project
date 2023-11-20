package comp3607_group_project;

import java.io.IOException;

/**
* The ProcessAssignmentCommand class is responsible for executing a sequence of tasks,
* including marking attributes, methods, constructors, and generating a PDF report.
*/
public class ProcessAssignmentCommand implements Command {
    /**
     * The TestSuite instance used for marking attributes, methods, constructors, and generating a PDF report.
     */
    private TestSuite tester;

    /**
     * Constructs a ProcessAssignmentCommand with the specified folder.
     *
     * @param folder The name of the folder containing the assignment files.
     */
    public ProcessAssignmentCommand(String folder) {
        tester = new TestSuite(folder);

    }

     /**
     * Executes the command to mark attributes, methods, constructors, and generate a PDF report.
     * Handles IOException if any of the tasks encounters an I/O error.
     */
    public void execute() {
        try {
            tester.markAttributes();
            tester.markMethods();
            tester.markConstructors();
            tester.generatePDF();

        } catch (IOException e) {
            e.printStackTrace();
        } 

    }
    
}
