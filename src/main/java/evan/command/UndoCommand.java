package evan.command;

import evan.Storage;
import evan.TaskList;
import evan.Ui;
import evan.exception.DukeException;

public class UndoCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.undo();
        storage.save(tasks.getTasks());
        return "Undone!";
    }
}
