package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Stack;

public class TaskListHistory {
    private Stack<ArrayList<Task>> history;

    public TaskListHistory() {
        this.history = new Stack<>();
    }

    public void saveState(ArrayList<Task> tasks) {
        ArrayList<Task> snapshot = new ArrayList<>();
        for (Task task : tasks) {
            snapshot.add(task.copy());
        }
        history.push(snapshot);
    }

    public ArrayList<Task> undo() {
        if (history.isEmpty()) {
            return null;
        }
        return history.pop();
    }

    public boolean canUndo() {
        return !history.isEmpty();
    }

    public void clear() {
        history.clear();
    }
}
