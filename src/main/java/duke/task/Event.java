package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event
     * @param from The start time in string format (yyyy-MM-dd HHmm)
     * @param to The end time in string format (yyyy-MM-dd HHmm)
     */
    public Event(String description, String from, String to) {
        super(description);
        assert from != null && !from.isEmpty() : "Event from string should not be null or empty";
        assert to != null && !to.isEmpty() : "Event to string should not be null or empty";
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
    }

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event
     * @param from The start time as a LocalDateTime object
     * @param to The end time as a LocalDateTime object
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        assert from != null : "Event from LocalDateTime should not be null";
        assert to != null : "Event to LocalDateTime should not be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time as a LocalDateTime object
     */
    public LocalDateTime getFrom() {
        assert from != null : "Event start time should not be null";
        return from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time as a LocalDateTime object
     */
    public LocalDateTime getTo() {
        assert to != null : "Event end time should not be null";
        return to;
    }

    /**
     * Gets the start time as a formatted string.
     *
     * @return The formatted start time string
     */
    public String getFromString() {
        return from.format(OUTPUT_FORMATTER);
    }

    /**
     * Gets the end time as a formatted string.
     *
     * @return The formatted end time string
     */
    public String getToString() {
        return to.format(OUTPUT_FORMATTER);
    }

    /**
     * Gets the type icon for an event task.
     * 
     * @return "[E]" representing an event task
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns a string representation of the event task.
     * 
     * @return String representation including type, status, description, and time period
     */
    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + " (from: " + getFromString() + " to: " + getToString() + ")";
    }
}
