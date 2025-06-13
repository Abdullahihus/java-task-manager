import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AppGUI extends Application {

    private ObservableList<Task> tasks;

    @Override
    public void start(Stage stage) {
        // heading on little window
        Label heading = new Label("Task Manager (GUI)");

        // field input box
        TextField titleInput = new TextField();
        titleInput.setPromptText("Task title"); //transparent text in box

        TextField dueDateInput = new TextField();
        dueDateInput.setPromptText("Due date (e.g. 2025-06-30)");

        // comboBox is a dropdown menu
        ComboBox<String> priorityInput = new ComboBox<>();
        // strings to add to dropdown
        priorityInput.getItems().addAll("Low", "Medium", "High");
        priorityInput.setPromptText("Priority");

        // buttons to add and delete tasks
        Button addButton = new Button("Add Task");
        Button deleteButton = new Button("Delete Selected Task");


        tasks = FXCollections.observableArrayList(); // automatically refreshes UI
        ListView<Task> listView = new ListView<>(tasks); // listview is the tasks that are added

        // Render Task objects using their toString()
        listView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                setText(empty || task == null ? null : task.toString());
            }
        });

        addButton.setOnAction(e -> {
            String title = titleInput.getText().trim();
            String dueDate = dueDateInput.getText().trim();
            String priority = priorityInput.getValue();

            if (!title.isEmpty() && dueDate != null && !dueDate.isEmpty() && priority != null) {
                tasks.add(new Task(title, dueDate, priority));
                titleInput.clear();
                dueDateInput.clear();
                priorityInput.setValue(null);
            }
        });

        deleteButton.setOnAction(e -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                tasks.remove(selectedIndex);
            }
        });

        listView.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                Task selected = listView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selected.toggleDone();
                    listView.refresh();
                }
            }
        });

        VBox root = new VBox(10,
            heading,
            titleInput,
            dueDateInput,
            priorityInput,
            addButton,
            deleteButton,
            listView
        );
        root.setStyle("-fx-padding: 20;");

        stage.setScene(new Scene(root, 400, 500));
        stage.setTitle("Task Manager");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
