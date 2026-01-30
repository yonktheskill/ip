package duke.exception;

/**
 * Custom exception class for Duke application errors.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
