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
        assert task != null : "Task to add should not be null";
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list.
     * 
     * @param tasks The task list
     * @param ui The user interface
     * @param storage The storage handler
     * @return The response message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null && storage != null : "Tasks and storage should not be null";
        tasks.add(task);
        storage.save(tasks.getTasks());
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
