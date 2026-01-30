/**
 * Command to unmark a task (mark as not done).
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(index);
        storage.save(tasks.getTasks());
        ui.showTaskUnmarked(task);
    }
}
