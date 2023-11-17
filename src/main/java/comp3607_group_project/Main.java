package comp3607_group_project;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running");
        ProcessFolderCommand processFolder = new ProcessFolderCommand("Joshua_Noel_816031055_A1");
        ProcessPdfCommand processPdf = new ProcessPdfCommand("Joshua_Noel_816031055_A1");
        processFolder.execute();
        processPdf.execute();

        ProcessAssignmentCommand processAssignment = new ProcessAssignmentCommand("Joshua_Noel_816031055_A1");
        processAssignment.execute();

        TestSuite tester = new TestSuite("Joshua_Noel_816031055_A1");
        tester.markAttributes();
        tester.markConstructors();
        tester.markMethods();
        tester.computeTotalMarks();
        System.out.println(tester.getAttrMarks());
        System.out.println(tester.getConstructorMarks());
        System.out.println(tester.getMethodMarks());
        System.out.println(tester.getTotalMarks());

    }
}