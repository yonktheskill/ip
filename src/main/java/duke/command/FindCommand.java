package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import java.util.ArrayList;

/**
 * Command to find tasks by searching for a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     * 
     * @param keyword The keyword to search for
     */
    public FindCommand(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Keyword should not be null or empty";
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasks should not be null";
        ArrayList<Task> matchingTasks = tasks.find(keyword);
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found in your list.";
        }
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            response.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        return response.toString().trim();
    }
}
