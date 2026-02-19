package evan;

import evan.task.Task;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Manages the history of task list states for undo functionality.
 */
public class TaskListHistory {
    private Stack<ArrayList<Task>> history;

    /**
     * Constructs a new TaskListHistory with an empty history stack.
     */
    public TaskListHistory() {
        this.history = new Stack<>();
    }

    /**
     * Saves a snapshot of the current task list state.
     * 
     * @param tasks The list of tasks to save
     */
    public void saveState(ArrayList<Task> tasks) {
        ArrayList<Task> snapshot = new ArrayList<>();
        for (Task task : tasks) {
            snapshot.add(task.copy());
        }
        history.push(snapshot);
    }

    /**
     * Retrieves the most recent saved state from history.
     * 
     * @return The previous task list state, or null if history is empty
     */
    public ArrayList<Task> undo() {
        if (history.isEmpty()) {
            return null;
        }
        return history.pop();
    }

    /**
     * Checks if there are any saved states available for undo.
     * 
     * @return True if undo is possible, false otherwise
     */
    public boolean canUndo() {
        return !history.isEmpty();
    }

    /**
     * Clears all saved history.
     */
    public void clear() {
        history.clear();
    }
}
