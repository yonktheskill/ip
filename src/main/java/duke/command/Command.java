package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

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
     * @return The response message from executing the command
     * @throws DukeException If an error occurs during command execution
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     * 
     * @return True if this is an exit command, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
