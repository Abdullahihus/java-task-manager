import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    manager.addTask(title);
                }
                case 2 -> {
                    manager.viewTasks();
                }
                case 3 -> {
                    if (manager.isEmpty()) {
                        System.out.println("No tasks to mark.");
                        break;
                    }
                    manager.viewTasks();
                    System.out.print("Enter task number to mark as done: ");
                    int doneIndex = scanner.nextInt();
                    scanner.nextLine(); // clear buffer
                    manager.markTaskDone(doneIndex);
                }
                case 4 -> {
                    manager.viewTasks();
                    System.out.print("Enter task number to delete: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine(); // clear buffer
                    manager.deleteTask(deleteIndex);
                }
                case 5 -> {
                    System.out.println("GoodBye!");
                    return;
                }
                case 6 -> {
                    System.out.print("Enter filename to save: ");
                    String filename = scanner.nextLine();
                    manager.saveToFile(filename);
                }
                case 7 -> {
                    System.out.println("Enter filename to laod: ");
                    String filename = scanner.nextLine();
                    manager.loadFromFile(filename);
                }

                default -> System.out.println("Invalid input");
            }
        }
    }

    public static void printMenu() {
        System.out.println("*************");
        System.out.println("Task Manager");
        System.out.println("*************");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Mark Task Done");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.println("6. Save Tasks to File");
        System.out.println("7. Load Tasks from File");
        System.out.print("Choose an option: ");
    }
}
