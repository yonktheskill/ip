package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
