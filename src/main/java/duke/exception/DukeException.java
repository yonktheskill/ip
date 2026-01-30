package duke.exception;

/**
 * Custom exception class for Duke application errors.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message
     */
    public DukeException(String message) {
        super(message);
    }
}
