import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
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
                String line = (task.isDone() ? "[x] " : "[ ] ") + 
                task.getTitle() + " | " + 
                task.getDueDate() + " | " + 
                task.getPriority();
                writer.write(line + "\n");
            }
            System.out.println("Tasks saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving tasks" + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        tasks.clear();
        //BufferReader: way to read file in java
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.trim().isEmpty()){
                    continue;
                }
                //split: splits at delimeter "|"
                //trim: gets rid of white space
                boolean done = line.startsWith("[x]");
                
                //split starting at title and for every "|" array store the words after it
                String[] parts = line.substring(4).split("\\|");
                
                //check if we have the whole task with title, dueDate and priority
                if(parts.length < 3){
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String title = parts[0].trim();
                String dueDate = parts[1].trim();
                String priority = parts[2].trim();

                Task task = new Task(title, dueDate, priority);
                if (done){
                    task.markDone();
                }

                tasks.add(task);

            }
            System.out.println("Task loaded from " + filename);
        } catch (IOException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
    }
}
