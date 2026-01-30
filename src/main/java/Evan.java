
import java.util.ArrayList;
import java.util.Scanner;

public class Evan {
    private static final String FILE_PATH = "./data/duke.txt";

    public static void main(String[] args) {
        String lineSeparator = "____________________________________________________________";
        System.out.println(lineSeparator);
        System.out.println(" Hey! I'm Evan");
        System.out.println(" What's up?");
        System.out.println(lineSeparator);

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> tasks = storage.load();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(lineSeparator);
                System.out.println("Don't leave me ):");
                System.out.println(lineSeparator);
                break;
            } else if (input.equals("list")) {
                System.out.println(lineSeparator);
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasks.get(i));
                }
                System.out.println(lineSeparator);
            } else if (input.startsWith("mark ")) {
                try {
                    int idx = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        tasks.get(idx).markDone();
                        storage.save(tasks);
                        System.out.println(lineSeparator);
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks.get(idx));
                        System.out.println(lineSeparator);
                    } else {
                        System.out.println(lineSeparator);
                        System.out.println(" Invalid task number.");
                        System.out.println(lineSeparator);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(lineSeparator);
                    System.out.println(" Please provide a valid task number.");
                    System.out.println(lineSeparator);
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int idx = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        tasks.get(idx).markUndone();
                        storage.save(tasks);
                        System.out.println(lineSeparator);
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(idx));
                        System.out.println(lineSeparator);
                    } else {
                        System.out.println(lineSeparator);
                        System.out.println(" Invalid task number.");
                        System.out.println(lineSeparator);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(lineSeparator);
                    System.out.println(" Please provide a valid task number.");
                    System.out.println(lineSeparator);
                }
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                Task newTask = new ToDo(description);
                storage.save(tasks);
                tasks.add(newTask);
                System.out.println(lineSeparator);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println(lineSeparator);
            } else if (input.startsWith("deadline ")) {
                try {
                    String remaining = input.substring(9);
                    int byIndex = remaining.indexOf("/by ");
                    if (byIndex != -1) {
                        String description = remaining.substring(0, byIndex).trim();
                        String by = remaining.substring(byIndex + 4).trim();
                        Task newTask = new Deadline(description, by);
                        tasks.add(newTask);
                        storage.save(tasks);
                        System.out.println(lineSeparator);
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + newTask);
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(lineSeparator);
                    }
                } catch (Exception e) {
                    System.out.println(lineSeparator);
                    System.out.println(" Invalid date format. Please use: yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)");
                    System.out.println(lineSeparator);
                }
            } else if (input.startsWith("event ")) {
                try {
                    String remaining = input.substring(6);
                    int fromIndex = remaining.indexOf("/from ");
                    int toIndex = remaining.indexOf("/to ");
                    if (fromIndex != -1 && toIndex != -1) {
                        String description = remaining.substring(0, fromIndex).trim();
                        String from = remaining.substring(fromIndex + 6, toIndex).trim();
                        String to = remaining.substring(toIndex + 4).trim();
                        Task newTask = new Event(description, from, to);
                        tasks.add(newTask);
                        storage.save(tasks);
                        System.out.println(lineSeparator);
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + newTask);
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(lineSeparator);
                    }
                } catch (Exception e) {
                    System.out.println(lineSeparator);
                    System.out.println(" Invalid date format. Please use: yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)");
                    System.out.println(lineSeparator);
                }
            } else if (input.startsWith("delete ")) {
                try {
                    int idx = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        Task removedTask = tasks.remove(idx);
                        storage.save(tasks);
                        System.out.println(lineSeparator);
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   " + removedTask);
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(lineSeparator);
                    } else {
                        System.out.println(lineSeparator);
                        System.out.println(" Invalid task number.");
                        System.out.println(lineSeparator);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(lineSeparator);
                    System.out.println(" Please provide a valid task number.");
                    System.out.println(lineSeparator);
                }
            } else {
                storage.save(tasks);
                Task newTask = new ToDo(input);
                tasks.add(newTask);
                System.out.println(lineSeparator);
                System.out.println(" added: " + input);
                System.out.println(lineSeparator);
            }
        }
        scanner.close();
    }
}
