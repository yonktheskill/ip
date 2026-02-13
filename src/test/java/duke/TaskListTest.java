package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the TaskList class.
 */
public class TaskListTest {
    private TaskList taskList;
    private Task task1;
    private Task task2;
    private Task task3;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        task1 = new ToDo("read book");
        task2 = new ToDo("return book");
        task3 = new ToDo("buy groceries");
    }

    @Test
    public void add_singleTask_success() {
        taskList.add(task1);
        assertEquals(1, taskList.size());
        assertEquals(task1, taskList.get(0));
    }

    @Test
    public void add_multipleTasks_success() {
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        assertEquals(3, taskList.size());
        assertEquals(task1, taskList.get(0));
        assertEquals(task2, taskList.get(1));
        assertEquals(task3, taskList.get(2));
    }

    @Test
    public void delete_validIndex_success() throws DukeException {
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        
        Task deleted = taskList.delete(1);
        
        assertEquals(task2, deleted);
        assertEquals(2, taskList.size());
        assertEquals(task1, taskList.get(0));
        assertEquals(task3, taskList.get(1));
    }

    @Test
    public void delete_firstTask_success() throws DukeException {
        taskList.add(task1);
        taskList.add(task2);
        
        Task deleted = taskList.delete(0);
        
        assertEquals(task1, deleted);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }

    @Test
    public void delete_lastTask_success() throws DukeException {
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        
        Task deleted = taskList.delete(2);
        
        assertEquals(task3, deleted);
        assertEquals(2, taskList.size());
    }

    @Test
    public void delete_negativeIndex_exceptionThrown() {
        taskList.add(task1);
        
        DukeException exception = assertThrows(DukeException.class, () -> {
            taskList.delete(-1);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }

    @Test
    public void delete_indexTooLarge_exceptionThrown() {
        taskList.add(task1);
        
        DukeException exception = assertThrows(DukeException.class, () -> {
            taskList.delete(5);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }

    @Test
    public void delete_emptyList_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () -> {
            taskList.delete(0);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }

    @Test
    public void mark_validIndex_success() throws DukeException {
        taskList.add(task1);
        assertFalse(task1.isDone());
        
        Task marked = taskList.mark(0);
        
        assertTrue(marked.isDone());
        assertEquals(task1, marked);
    }

    @Test
    public void mark_multipleTasksIndividually_success() throws DukeException {
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        
        taskList.mark(0);
        taskList.mark(2);
        
        assertTrue(task1.isDone());
        assertFalse(task2.isDone());
        assertTrue(task3.isDone());
    }

    @Test
    public void mark_negativeIndex_exceptionThrown() {
        taskList.add(task1);
        
        DukeException exception = assertThrows(DukeException.class, () -> {
            taskList.mark(-1);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }

    @Test
    public void mark_indexTooLarge_exceptionThrown() {
        taskList.add(task1);
        
        DukeException exception = assertThrows(DukeException.class, () -> {
            taskList.mark(10);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }

    @Test
    public void unmark_markedTask_success() throws DukeException {
        taskList.add(task1);
        task1.markDone();
        assertTrue(task1.isDone());
        
        Task unmarked = taskList.unmark(0);
        
        assertFalse(unmarked.isDone());
        assertEquals(task1, unmarked);
    }

    @Test
    public void unmark_negativeIndex_exceptionThrown() {
        taskList.add(task1);
        
        DukeException exception = assertThrows(DukeException.class, () -> {
            taskList.unmark(-5);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }

    @Test
    public void unmark_indexTooLarge_exceptionThrown() {
        taskList.add(task1);
        
        DukeException exception = assertThrows(DukeException.class, () -> {
            taskList.unmark(1);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }

    @Test
    public void size_emptyList_returnsZero() {
        assertEquals(0, taskList.size());
    }

    @Test
    public void size_withTasks_returnsCorrectSize() {
        taskList.add(task1);
        assertEquals(1, taskList.size());
        
        taskList.add(task2);
        assertEquals(2, taskList.size());
        
        taskList.add(task3);
        assertEquals(3, taskList.size());
    }

    @Test
    public void constructor_withArrayList_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        
        TaskList newTaskList = new TaskList(tasks);
        
        assertEquals(2, newTaskList.size());
        assertEquals(task1, newTaskList.get(0));
        assertEquals(task2, newTaskList.get(1));
    }

    @Test
    public void getTasks_returnsCorrectArrayList() {
        taskList.add(task1);
        taskList.add(task2);
        
        ArrayList<Task> tasks = taskList.getTasks();
        
        assertEquals(2, tasks.size());
        assertEquals(task1, tasks.get(0));
        assertEquals(task2, tasks.get(1));
    }

    @Test
    public void markAndDelete_success() throws DukeException {
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        
        taskList.mark(1);
        assertTrue(task2.isDone());
        
        taskList.delete(1);
        assertEquals(2, taskList.size());
        assertFalse(taskList.get(1).isDone()); // task3 is now at index 1
    }
}
