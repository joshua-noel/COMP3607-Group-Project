package comp3607_group_project;

 

public class ProcessFolderCommand implements Command {
    FileHandler handler = new FileHandler();
    private String folder;

    public ProcessFolderCommand(String folder) {
        this.folder = folder;

    }

    public void execute() {
        handler.unzip(folder);
        handler.appendFiles(folder);
    }
    
}
