package comp3607_group_project;

 

public class Main {
    public static void main(String[] args) {
        System.out.println("Running");
        FileHandler handler = new FileHandler();
        ProcessFolderCommand processFolder = new ProcessFolderCommand("Joshua_Noel_816031055_A1");
        processFolder.execute();
        handler.getRubicText("Joshua_Noel_816031055_A1");
    }
}