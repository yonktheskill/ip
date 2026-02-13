package duke.task;

/**
 * Represents a todo task without any date/time attached.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the todo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gets the type icon for a to-do task.
     * 
     * @return "[T]" representing a to-do task
     */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public Task copy() {
        ToDo copied = new ToDo(this.description);
        if (this.isDone) {
            copied.markDone();
        }
        return copied;
    }
}
