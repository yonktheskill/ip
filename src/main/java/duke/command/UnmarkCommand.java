package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Command to unmark a task (mark as not done).
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand to unmark the task at the specified index.
     *
     * @param index The index of the task to mark as not done
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(index);
        storage.save(tasks.getTasks());
        ui.showTaskUnmarked(task);
    }
}
