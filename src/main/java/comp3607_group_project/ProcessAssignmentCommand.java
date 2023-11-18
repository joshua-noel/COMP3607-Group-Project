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
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tester.markMethods();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tester.markConstructors();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tester.generatePDF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tester.computeTotalMarks();
        System.out.println(tester.getTotalMarks());     

    }
    
}
