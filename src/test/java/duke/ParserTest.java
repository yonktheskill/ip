package duke;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the Parser class.
 */
public class ParserTest {

    @Test
    public void parse_exitCommand_success() throws DukeException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parse_listCommand_success() throws DukeException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_validMarkCommand_success() throws DukeException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parse_validUnmarkCommand_success() throws DukeException {
        Command command = Parser.parse("unmark 2");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void parse_validDeleteCommand_success() throws DukeException {
        Command command = Parser.parse("delete 3");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parse_validTodoCommand_success() throws DukeException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_emptyTodoDescription_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("todo");
        });
        assertEquals("The description of a todo cannot be empty.", exception.getMessage());
    }

    @Test
    public void parse_todoWithOnlySpaces_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("todo   ");
        });
        assertEquals("The description of a todo cannot be empty.", exception.getMessage());
    }

    @Test
    public void parse_validDeadlineCommand_success() throws DukeException {
        Command command = Parser.parse("deadline return book /by 2024-12-31 2359");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_deadlineWithoutBy_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("deadline return book");
        });
        assertEquals("Please specify the deadline using /by.", exception.getMessage());
    }

    @Test
    public void parse_deadlineWithEmptyDescription_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("deadline /by 2024-12-31 2359");
        });
        assertEquals("The description of a deadline cannot be empty.", exception.getMessage());
    }

    @Test
    public void parse_deadlineWithEmptyTime_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("deadline return book /by ");
        });
        assertEquals("The deadline time cannot be empty.", exception.getMessage());
    }

    @Test
    public void parse_validEventCommand_success() throws DukeException {
        Command command = Parser.parse("event project meeting /from 2024-01-15 1400 /to 2024-01-15 1600");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_eventWithoutFrom_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("event meeting /to 2024-01-15 1600");
        });
        assertEquals("Please specify the event period using /from and /to.", exception.getMessage());
    }

    @Test
    public void parse_eventWithoutTo_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("event meeting /from 2024-01-15 1400");
        });
        assertEquals("Please specify the event period using /from and /to.", exception.getMessage());
    }

    @Test
    public void parse_eventWithEmptyDescription_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("event /from 2024-01-15 1400 /to 2024-01-15 1600");
        });
        assertEquals("The description of an event cannot be empty.", exception.getMessage());
    }

    @Test
    public void parse_eventWithEmptyTimes_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("event meeting /from  /to ");
        });
        assertEquals("The event start and end times cannot be empty.", exception.getMessage());
    }

    @Test
    public void parse_markCommandWithInvalidNumber_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("mark abc");
        });
        assertEquals("Please provide a valid task number.", exception.getMessage());
    }

    @Test
    public void parse_markCommandWithoutNumber_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("mark");
        });
        assertEquals("Please provide a valid task number.", exception.getMessage());
    }

    @Test
    public void parse_unmarkCommandWithInvalidNumber_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("unmark xyz");
        });
        assertEquals("Please provide a valid task number.", exception.getMessage());
    }

    @Test
    public void parse_deleteCommandWithInvalidNumber_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parse("delete invalid");
        });
        assertEquals("Please provide a valid task number.", exception.getMessage());
    }

    @Test
    public void parse_unknownCommand_treatedAsTodo() throws DukeException {
        Command command = Parser.parse("random text");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_commandWithExtraSpaces_success() throws DukeException {
        Command command = Parser.parse("  todo    read book  ");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_caseInsensitiveCommand_success() throws DukeException {
        Command command1 = Parser.parse("BYE");
        assertTrue(command1 instanceof ExitCommand);
        
        Command command2 = Parser.parse("LiSt");
        assertTrue(command2 instanceof ListCommand);
    }
}
