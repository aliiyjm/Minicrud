package com.example.minicrud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;


public class HelloController {
    @FXML private TableView<Task> taskTable;
    @FXML private TableColumn<Task, String> titleCol;
    @FXML private TableColumn<Task, String> descCol;
    @FXML private TableColumn<Task, String> priorityCol;
    @FXML private TableColumn<Task, LocalDate> dateCol;
    @FXML private TextField titleField;
    @FXML private TextArea descField;
    @FXML private ComboBox<String> priorityCombo;
    @FXML private DatePicker datePicker;

    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private int taskIdCounter = 1;

    @FXML
    public void initialize() {
        priorityCombo.getItems().addAll("Alta", "Media", "Baja");

        titleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        descCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        priorityCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriority()));
        dateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDueDate()));

        taskTable.setItems(tasks);
    }

    @FXML
    private void addTask() {
        Task newTask = new Task(taskIdCounter++, titleField.getText(),
                descField.getText(), priorityCombo.getValue(),
                datePicker.getValue());
        tasks.add(newTask);
        clearFields();
    }

    @FXML
    private void editTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            selectedTask.setTitle(titleField.getText());
            selectedTask.setDescription(descField.getText());
            selectedTask.setPriority(priorityCombo.getValue());
            selectedTask.setDueDate(datePicker.getValue());
            taskTable.refresh();
            clearFields();
        }
    }

    @FXML
    private void deleteTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        tasks.remove(selectedTask);
    }

    @FXML
    private void clearFields() {
        titleField.clear();
        descField.clear();
        priorityCombo.setValue(null);
        datePicker.setValue(null);
    }
}
