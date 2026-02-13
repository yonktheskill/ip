package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.*;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses user input and returns the corresponding Command.
     * 
     * @param fullCommand The full command string entered by the user
     * @return The Command object corresponding to the user input
     * @throws DukeException If the command format is invalid
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.trim().split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return parseMarkCommand(fullCommand);
            case "unmark":
                return parseUnmarkCommand(fullCommand);
            case "delete":
                return parseDeleteCommand(fullCommand);
            case "todo":
                return parseTodoCommand(fullCommand);
            case "deadline":
                return parseDeadlineCommand(fullCommand);
            case "event":
                return parseEventCommand(fullCommand);
            case "find":
                return parseFindCommand(fullCommand);
            case "undo":
                return new UndoCommand();
            default:
                return new AddCommand(new ToDo(fullCommand));
        }
    }

    /**
     * Parses a mark command.
     * 
     * @param fullCommand The full command string
     * @return A MarkCommand
     * @throws DukeException If the command format is invalid
     */
    private static Command parseMarkCommand(String fullCommand) throws DukeException {
        try {
            int index = Integer.parseInt(fullCommand.substring(5).trim()) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException("Please provide a valid task number.");
        }
    }

    /**
     * Parses an unmark command.
     * 
     * @param fullCommand The full command string
     * @return An UnmarkCommand
     * @throws DukeException If the command format is invalid
     */
    private static Command parseUnmarkCommand(String fullCommand) throws DukeException {
        try {
            int index = Integer.parseInt(fullCommand.substring(7).trim()) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException("Please provide a valid task number.");
        }
    }

    /**
     * Parses a delete command.
     * 
     * @param fullCommand The full command string
     * @return A DeleteCommand
     * @throws DukeException If the command format is invalid
     */
    private static Command parseDeleteCommand(String fullCommand) throws DukeException {
        try {
            int index = Integer.parseInt(fullCommand.substring(7).trim()) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException("Please provide a valid task number.");
        }
    }

    /**
     * Parses a todo command.
     * 
     * @param fullCommand The full command string
     * @return An AddCommand with a ToDo task
     * @throws DukeException If the command format is invalid
     */
    private static Command parseTodoCommand(String fullCommand) throws DukeException {
        if (fullCommand.length() <= 5) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String description = fullCommand.substring(5).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new ToDo(description));
    }

    /**
     * Parses a deadline command.
     * 
     * @param fullCommand The full command string
     * @return An AddCommand with a Deadline task
     * @throws DukeException If the command format is invalid
     */
    private static Command parseDeadlineCommand(String fullCommand) throws DukeException {
        try {
            String remaining = fullCommand.substring(9);
            int byIndex = remaining.indexOf("/by ");
            if (byIndex == -1) {
                throw new DukeException("Please specify the deadline using /by.");
            }
            String description = remaining.substring(0, byIndex).trim();
            if (description.isEmpty()) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String by = remaining.substring(byIndex + 4).trim();
            if (by.isEmpty()) {
                throw new DukeException("The deadline time cannot be empty.");
            }
            return new AddCommand(new Deadline(description, by));
        } catch (DukeException e) {
            throw e;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please provide a valid deadline format: deadline <description> /by <time>");
        } catch (Exception e) {
            throw new DukeException("Invalid date format. Please use: yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)");
        }
    }

    /**
     * Parses an event command.
     * 
     * @param fullCommand The full command string
     * @return An AddCommand with an Event task
     * @throws DukeException If the command format is invalid
     */
    private static Command parseEventCommand(String fullCommand) throws DukeException {
        try {
            String remaining = fullCommand.substring(6);
            int fromIndex = remaining.indexOf("/from ");
            int toIndex = remaining.indexOf("/to ");
            if (fromIndex == -1 || toIndex == -1) {
                throw new DukeException("Please specify the event period using /from and /to.");
            }
            String description = remaining.substring(0, fromIndex).trim();
            if (description.isEmpty()) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String from = remaining.substring(fromIndex + 6, toIndex).trim();
            String to = remaining.substring(toIndex + 4).trim();
            if (from.isEmpty() || to.isEmpty()) {
                throw new DukeException("The event start and end times cannot be empty.");
            }
            return new AddCommand(new Event(description, from, to));
        } catch (DukeException e) {
            throw e;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please provide a valid event format: event <description> /from <start> /to <end>");
        } catch (Exception e) {
            throw new DukeException("Invalid date format. Please use: yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)");
        }
    }

    /**
     * Parses a find command.
     * 
     * @param fullCommand The full command string
     * @return A FindCommand with the search keyword
     * @throws DukeException If the command format is invalid
     */
    private static Command parseFindCommand(String fullCommand) throws DukeException {
        if (fullCommand.length() <= 5) {
            throw new DukeException("Please provide a keyword to search for.");
        }
        String keyword = fullCommand.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new DukeException("Please provide a keyword to search for.");
        }
        return new FindCommand(keyword);
    }
}
