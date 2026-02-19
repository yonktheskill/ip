package evan;

import evan.command.Command;
import evan.exception.DukeException;

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
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        assert ui != null && storage != null && tasks != null : "Components should be initialized";
    }

    /**
     * Gets a response from Evan for the given user input.
     * This method is used by the GUI to process commands.
     * 
     * @param input The user's input command
     * @return Evan's response as a String
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        try {
            Command command = Parser.parse(input);
            if (command.isUndoable()) {
                tasks.saveState();
            }
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
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
                if (command.isUndoable()) {
                    tasks.saveState();
                }
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
