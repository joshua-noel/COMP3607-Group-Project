package comp3607_group_project;

/**
* The ProcessFolderCommand class is responsible for executing tasks related to handling files in a folder,
* including unzipping the folder and appending package information to Java files.
*/
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
