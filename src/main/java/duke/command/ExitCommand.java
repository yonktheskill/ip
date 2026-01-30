package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying the goodbye message.
     * 
     * @param tasks The task list
     * @param ui The user interface
     * @param storage The storage handler
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
