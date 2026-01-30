package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand to mark the task at the specified index.
     *
     * @param index The index of the task to mark as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by marking the task as done.
     * 
     * @param tasks The task list
     * @param ui The user interface
     * @param storage The storage handler
     * @throws DukeException If the task index is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.mark(index);
        storage.save(tasks.getTasks());
        ui.showTaskMarked(task);
    }
}
