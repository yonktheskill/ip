package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Main class for the Evan chatbot application.
 */
public class Evan {
    private static final String FILE_PATH = "./data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an Evan chatbot with the specified file path for storage.
     * 
     * @param filePath The path to the file where tasks are stored
     */
    public Evan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main logic of the chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * Main entry point of the application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        new Evan(FILE_PATH).run();
    }
}
