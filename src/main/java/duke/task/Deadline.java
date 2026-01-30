package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task
     * @param by The deadline in string format (yyyy-MM-dd HHmm)
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
    }

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task
     * @param by The deadline as a LocalDateTime object
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the deadline date and time.
     *
     * @return The deadline as a LocalDateTime object
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Gets the deadline as a formatted string.
     *
     * @return The formatted deadline string
     */
    public String getByString() {
        return by.format(OUTPUT_FORMATTER);
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + " (by: " + getByString() + ")";
    }
}
