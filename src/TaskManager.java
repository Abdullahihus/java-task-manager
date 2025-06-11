import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String title) {
        Task newTask = new Task(title);
        tasks.add(newTask);
        System.out.println("Task added");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    public void markTaskDone(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.markDone();
            System.out.println("Taks marked as done");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void deleteTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.remove(index - 1);
            System.out.println("Taks deleted");
        } else {
            System.out.println("Invalid task number");
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Task task : tasks) {
                String line = (task.isDone() ? "[x] " : "[ ] ") + task.getTitle();
                writer.write(line + "\n");
            }
            System.out.println("Tasks saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving tasks" + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        tasks.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                boolean done = line.startsWith("[x]");
                String title = line.length() > 4 ? line.substring(4) : "Untitled Task";
                Task task = new Task(title);
                if (done)
                    task.markDone();
                tasks.add(task);

            }
            System.out.println("Task loaded from " + filename);
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
    }
}
