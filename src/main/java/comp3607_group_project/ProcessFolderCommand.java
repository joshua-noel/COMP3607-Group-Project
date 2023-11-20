package comp3607_group_project;

/**
* The ProcessFolderCommand class is responsible for executing tasks related to handling files in a folder,
* including unzipping the folder and appending package information to Java files.
*/
public class ProcessFolderCommand implements Command {
    /**
     * The FileHandler instance used for handling file-related tasks.
     */
    FileHandler handler = new FileHandler();

    /**
     * The name of the folder to be processed.
     */
    private String folder;

    /**
     * Constructs a ProcessFolderCommand with the specified folder name.
     *
     * @param folder The name of the folder to be processed.
     */
    public ProcessFolderCommand(String folder) {
        this.folder = folder;

    }

    /**
     * Executes the command to unzip the folder and append package information to Java files.
     */
    public void execute() {
        handler.unzip(folder);
        handler.appendFiles(folder);
    }
    
}
