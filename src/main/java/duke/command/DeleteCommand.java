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
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.delete(index);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(task, tasks.size());
    }
}
