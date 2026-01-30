/**
 * Abstract class representing a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     * 
     * @param tasks The task list
     * @param ui The UI
     * @param storage The storage
     * @throws DukeException If an error occurs during command execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     * 
     * @return True if this is an exit command, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
