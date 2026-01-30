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

    @Override
    public String getTypeIcon() {
        return "[T]";
    }
}
