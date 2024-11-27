package upm.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class JavaFXPickersExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main Layout
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        // DatePicker
        Label datePickerLabel = new Label("Select a Date:");
        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(e -> {
            System.out.println("Selected Date: " + datePicker.getValue());
        });

        // ColorPicker
        Label colorPickerLabel = new Label("Pick a Color:");
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> {
            System.out.println("Selected Color: " + colorPicker.getValue());
        });

        // FileChooser Button
        Button fileChooserButton = new Button("Choose a File");
        Label fileChooserLabel = new Label("No file selected");
        fileChooserButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                fileChooserLabel.setText("Selected File: " + selectedFile.getAbsolutePath());
            } else {
                fileChooserLabel.setText("No file selected");
            }
        });

        // DirectoryChooser Button
        Button directoryChooserButton = new Button("Choose a Directory");
        Label directoryChooserLabel = new Label("No directory selected");
        directoryChooserButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Open Directory");
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                directoryChooserLabel.setText("Selected Directory: " + selectedDirectory.getAbsolutePath());
            } else {
                directoryChooserLabel.setText("No directory selected");
            }
        });

        // Add Components to Layout
        root.getChildren().addAll(
                datePickerLabel, datePicker,
                colorPickerLabel, colorPicker,
                fileChooserButton, fileChooserLabel,
                directoryChooserButton, directoryChooserLabel
        );

        // Set up the Scene and Stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("JavaFX Pickers Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

