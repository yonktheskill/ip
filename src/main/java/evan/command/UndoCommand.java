package evan.command;

import evan.Storage;
import evan.TaskList;
import evan.Ui;
import evan.exception.DukeException;

/**
 * Command to undo the last undoable operation.
 */
public class UndoCommand extends Command {
    /**
     * Executes the undo command by restoring the previous task list state.
     * 
     * @param tasks The task list
     * @param ui The user interface
     * @param storage The storage handler
     * @return The response message
     * @throws DukeException If there is nothing to undo
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.undo();
        storage.save(tasks.getTasks());
        return "Undone!";
    }
}
