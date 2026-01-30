import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     * Handles the case where the file or directory doesn't exist.
     * 
     * @return ArrayList of tasks loaded from the file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        
        // Check if file exists
        if (!Files.exists(path)) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Parses a line from the file into a Task object.
     * Format: T | 1 | description
     *         D | 0 | description | by
     *         E | 0 | description | from | to
     * 
     * @param line The line to parse
     * @return The parsed Task object, or null if the line is invalid
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;

        switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                if (parts.length >= 4) {
                    task = new Deadline(description, parts[3]);
                }
                break;
            case "E":
                if (parts.length >= 5) {
                    task = new Event(description, parts[3], parts[4]);
                }
                break;
            default:
                return null;
        }

        if (task != null && isDone) {
            task.markDone();
        }

        return task;
    }

    /**
     * Saves tasks to the file.
     * Creates the directory if it doesn't exist.
     * 
     * @param tasks The list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        try {
            // Create directory if it doesn't exist
            Path path = Paths.get(filePath);
            Path parentDir = path.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            // Write tasks to file
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(taskToFileFormat(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a Task object to the file format string.
     * Format: T | 1 | description
     *         D | 0 | description | by
     *         E | 0 | description | from | to
     * 
     * @param task The task to convert
     * @return The formatted string
     */
    private String taskToFileFormat(Task task) {
        String type;
        String status = task.isDone() ? "1" : "0";
        StringBuilder sb = new StringBuilder();

        if (task instanceof ToDo) {
            type = "T";
            sb.append(type).append(" | ").append(status).append(" | ").append(task.getDescription());
        } else if (task instanceof Deadline) {
            type = "D";
            Deadline deadline = (Deadline) task;
            sb.append(type).append(" | ").append(status).append(" | ")
                    .append(task.getDescription()).append(" | ").append(deadline.getBy());
        } else if (task instanceof Event) {
            type = "E";
            Event event = (Event) task;
            sb.append(type).append(" | ").append(status).append(" | ")
                    .append(task.getDescription()).append(" | ").append(event.getFrom())
                    .append(" | ").append(event.getTo());
        }

        return sb.toString();
    }
}
