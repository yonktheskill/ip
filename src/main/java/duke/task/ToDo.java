package duke.task;

/**
 * Represents a simple to-do task without any date/time.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified description.
     * 
     * @param description The description of the to-do task
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
}
