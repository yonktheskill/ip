package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to add a task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand to add the specified task.
     *
     * @param task The task to add
     */
    /**
     * Constructs an AddCommand with the specified task.
     * 
     * @param task The task to add
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list.
     * 
     * @param tasks The task list
     * @param ui The user interface
     * @param storage The storage handler
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }
}
