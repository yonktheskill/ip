import java.util.ArrayList;

/**
 * Contains the task list with operations to add/delete/mark tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to add
     */
    public void add(Task task) {
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
        return tasks.size();
    }

    /**
     * Gets the ArrayList of tasks.
     * 
     * @return The ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
