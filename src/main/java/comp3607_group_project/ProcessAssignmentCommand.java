package comp3607_group_project;

import java.io.IOException;

/**
* The ProcessAssignmentCommand class is responsible for executing a sequence of tasks,
* including marking attributes, methods, constructors, and generating a PDF report.
*/
public class ProcessAssignmentCommand implements Command {
    private TestSuite tester;

    public ProcessAssignmentCommand(String folder) {
        tester = new TestSuite(folder);

    }

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
