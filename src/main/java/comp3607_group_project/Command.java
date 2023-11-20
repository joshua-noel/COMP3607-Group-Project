package comp3607_group_project;


/**
 * The Command interface represents an executable command.
 * Classes ProcessAssignmentCommand, ProcessFolderCommand,  implementing this interface should provide
 * functionality through the execute method.
 */
public interface Command {
    public void execute();
}
