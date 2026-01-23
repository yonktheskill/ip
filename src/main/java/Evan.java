
import java.util.Scanner;

public class Evan {
    public static void main(String[] args) {
        String lineSeparator = "____________________________________________________________";
        System.out.println(lineSeparator);
        System.out.println(" Hey! I'm Evan");
        System.out.println(" What's up?");
        System.out.println(lineSeparator);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println(lineSeparator);
            } else if (input.startsWith("mark ")) {
                try {
                    int idx = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (idx >= 0 && idx < taskCount) {
                        tasks[idx].markDone();
                        System.out.println(lineSeparator);
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks[idx]);
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
                    if (idx >= 0 && idx < taskCount) {
                        tasks[idx].markUndone();
                        System.out.println(lineSeparator);
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[idx]);
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
                if (taskCount < 100) {
                    tasks[taskCount] = new ToDo(description);
                    taskCount++;
                    System.out.println(lineSeparator);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println(lineSeparator);
                }
            } else if (input.startsWith("deadline ")) {
                String remaining = input.substring(9);
                int byIndex = remaining.indexOf("/by ");
                if (byIndex != -1) {
                    String description = remaining.substring(0, byIndex).trim();
                    String by = remaining.substring(byIndex + 4).trim();
                    if (taskCount < 100) {
                        tasks[taskCount] = new Deadline(description, by);
                        taskCount++;
                        System.out.println(lineSeparator);
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks[taskCount - 1]);
                        System.out.println(" Now you have " + taskCount + " tasks in the list.");
                        System.out.println(lineSeparator);
                    }
                }
            } else if (input.startsWith("event ")) {
                String remaining = input.substring(6);
                int fromIndex = remaining.indexOf("/from ");
                int toIndex = remaining.indexOf("/to ");
                if (fromIndex != -1 && toIndex != -1) {
                    String description = remaining.substring(0, fromIndex).trim();
                    String from = remaining.substring(fromIndex + 6, toIndex).trim();
                    String to = remaining.substring(toIndex + 4).trim();
                    if (taskCount < 100) {
                        tasks[taskCount] = new Event(description, from, to);
                        taskCount++;
                        System.out.println(lineSeparator);
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks[taskCount - 1]);
                        System.out.println(" Now you have " + taskCount + " tasks in the list.");
                        System.out.println(lineSeparator);
                    }
                }
            } else {
                if (taskCount < 100) {
                    tasks[taskCount] = new ToDo(input);
                    taskCount++;
                }
                System.out.println(lineSeparator);
                System.out.println(" added: " + input);
                System.out.println(lineSeparator);
            }
        }
        scanner.close();
    }
}
