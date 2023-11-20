package comp3607_group_project;

/**
 * The Command interface represents a command that can be executed.
 * Classes implementing this interface must provide an implementation
 * for the execute() method to define the action associated with the command.
 */
public interface Command {

    /**
     * Executes the command.
     */
    public void execute();
}
