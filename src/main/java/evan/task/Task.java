package evan.task;

/**
 * Represents an abstract task with a description and completion status.
 * This is the base class for all task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Gets the status icon representing whether the task is done.
     *
     * @return [X] if done, [ ] if not done
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Gets the type icon for the task.
     *
     * @return The type icon string
     */
    public String getTypeIcon() {
        return "[T]";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted task string
     */
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description;
    }

    /**
     * Creates a copy of this task.
     * 
     * @return A new Task object with the same properties
     */
    public abstract Task copy();
}
