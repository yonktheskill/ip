
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
                        System.out.println(" Going back on your word? Okay, I've unmarked this task:");
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
            } else {
                if (taskCount < 100) {
                    tasks[taskCount] = new Task(input);
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
