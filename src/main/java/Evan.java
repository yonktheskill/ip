import java.util.Scanner;

public class Evan {
    public static void main(String[] args) {
        String lineSeparator = "____________________________________________________________";
        System.out.println(lineSeparator);
        System.out.println(" Hey! I'm Evan");
        System.out.println(" What's up?");
        System.out.println(lineSeparator);

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(lineSeparator);
            } else {
                if (taskCount < 100) {
                    tasks[taskCount] = input;
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
