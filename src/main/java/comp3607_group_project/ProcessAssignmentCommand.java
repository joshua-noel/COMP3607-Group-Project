package comp3607_group_project;

public class ProcessAssignmentCommand implements Command {
    private TestSuite tester;

    public ProcessAssignmentCommand(String folder) {
        tester = new TestSuite(folder);

    }

    public void execute() {
        tester.markAttributes();
        tester.markMethods();
        tester.markConstructors();
        tester.computeTotalMarks();
        System.out.println(tester.getTotalMarks());     

    }
    
}
