package evan;

import evan.exception.DukeException;
import evan.task.Task;
import java.util.ArrayList;

/**
 * Manages a list of tasks and provides operations to add, delete, mark, and search tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private TaskListHistory history;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.history = new TaskListHistory();
    }

    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";
        this.tasks = tasks;
        this.history = new TaskListHistory();
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to add
     */
    public void add(Task task) {
        assert task != null : "Task to add should not be null";
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     * 
     * @param index The index of the task to delete
     * @return The deleted task
     * @throws DukeException If the index is invalid
     */
    public Task delete(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        assert index >= 0 && index < tasks.size() : "Index should be valid at this point";
        return tasks.remove(index);
    }

    /**
     * Marks a task as done.
     * 
     * @param index The index of the task to mark
     * @return The marked task
     * @throws DukeException If the index is invalid
     */
    public Task mark(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        assert index >= 0 && index < tasks.size() : "Index should be valid at this point";
        tasks.get(index).markDone();
        return tasks.get(index);
    }

    /**
     * Marks a task as not done.
     * 
     * @param index The index of the task to unmark
     * @return The unmarked task
     * @throws DukeException If the index is invalid
     */
    public Task unmark(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        assert index >= 0 && index < tasks.size() : "Index should be valid at this point";
        tasks.get(index).markUndone();
        return tasks.get(index);
    }

    /**
     * Gets a task from the list.
     * 
     * @param index The index of the task to get
     * @return The task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the size of the task list.
     * 
     * @return The number of tasks in the list
     */
    public int size() {
        int size = tasks.size();
        assert size >= 0 : "Task list size should be non-negative";
        return size;
    }

    /**
     * Gets the ArrayList of tasks.
     * 
     * @return The ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        assert tasks != null : "Tasks list should never be null";
        return tasks;
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     * 
     * @param keyword The keyword to search for
     * @return An ArrayList of tasks that match the keyword
     */
    public ArrayList<Task> find(String keyword) {
        assert keyword != null : "Keyword should not be null";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Saves the current state of the task list for undo functionality.
     */
    public void saveState() {
        history.saveState(tasks);
    }

    /**
     * Restores the task list to the previous saved state.
     * 
     * @throws DukeException If there is nothing to undo
     */
    public void undo() throws DukeException {
        if (!history.canUndo()) {
            throw new DukeException("Nothing to undo.");
        }
        this.tasks = history.undo();
    }
}
