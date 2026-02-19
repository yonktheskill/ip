package evan;

import evan.task.Task;
import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println(" Hey! I'm Evan");
        System.out.println(" What's up?");
        showLine();
    }

    /**
     * Displays the line separator.
     */
    public void showLine() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Reads a command from the user.
     * 
     * @return The command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message.
     * 
     * @param message The error message to display
     */
    public void showError(String message) {
        System.out.println(" " + message);
    }

    /**
     * Displays a loading error message.
     */
    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Don't leave me ):");
    }

    /**
     * Displays the list of tasks.
     * 
     * @param tasks The task list to display
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays a message when a task is marked as done.
     * 
     * @param task The task that was marked as done
     */
    public void showTaskMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    /**
     * Displays a message when a task is marked as not done.
     * 
     * @param task The task that was marked as not done
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    /**
     * Displays a message when a task is added.
     * 
     * @param task The task that was added
     * @param taskCount The total number of tasks in the list
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message when a task is deleted.
     * 
     * @param task The task that was deleted
     * @param taskCount The total number of tasks remaining in the list
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays the matching tasks found by the find command.
     * 
     * @param matchingTasks The list of tasks that match the search
     */
    public void showFoundTasks(java.util.ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println(" No matching tasks found in your list.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}
