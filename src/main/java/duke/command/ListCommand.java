package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks.
     * 
     * @param tasks The task list
     * @param ui The user interface
     * @param storage The storage handler
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
