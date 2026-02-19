package evan.command;

import evan.Storage;
import evan.TaskList;
import evan.Ui;

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
     * @return The response message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            return "You have no tasks in your list.";
        }
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return response.toString().trim();
    }
}
