package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
