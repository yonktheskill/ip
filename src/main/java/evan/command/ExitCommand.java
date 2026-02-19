package evan.command;

import evan.Storage;
import evan.TaskList;
import evan.Ui;

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
     * @return The response message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Don't leave me ):";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
