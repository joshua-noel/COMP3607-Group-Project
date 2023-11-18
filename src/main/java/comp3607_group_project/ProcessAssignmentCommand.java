package comp3607_group_project;

import java.io.IOException;

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
