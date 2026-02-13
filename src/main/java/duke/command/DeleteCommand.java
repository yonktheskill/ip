package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand to delete the task at the specified index.
     *
     * @param index The index of the task to delete
     */
    public DeleteCommand(int index) {
        assert index >= -1 : "Index should be at least -1";
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task from the task list.
     * 
     * @param tasks The task list
     * @param ui The user interface
     * @param storage The storage handler
     * @return The response message
     * @throws DukeException If the task index is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null && storage != null : "Tasks and storage should not be null";
        Task task = tasks.delete(index);
        storage.save(tasks.getTasks());
        return "Noted. I've removed this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
