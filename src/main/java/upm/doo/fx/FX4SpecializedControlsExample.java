package upm.doo.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FX4SpecializedControlsExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Main Layout
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        // Bottom
        HBox hBoxTop = new HBox();
        hBoxTop.setPadding(new Insets(2));
        hBoxTop.setAlignment(Pos.CENTER);
        hBoxTop.setStyle("-fx-background-color: #37474F;");
        Label status = new Label();
        status.setStyle("-fx-text-fill: white;");
        hBoxTop.getChildren().add(status);

        // DatePicker
        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(e -> status.setText("Selected Date: " + datePicker.getValue()));

        // ColorPicker
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> status.setText("Selected Color: " + colorPicker.getValue()));

        // FileChooser Button
        Button fileChooserButton = new Button("Choose a File");
        fileChooserButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                status.setText(selectedFile.getAbsolutePath());
            } else {
                status.setText("No file selected");
            }
        });

        // DirectoryChooser Button
        Button directoryChooserButton = new Button("Choose a Directory");
        directoryChooserButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Open Directory");
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                status.setText(selectedDirectory.getAbsolutePath());
            } else {
                status.setText("No directory selected");
            }
        });

        // Add Components to Layout
        root.getChildren().addAll(datePicker, colorPicker, fileChooserButton, directoryChooserButton);

        // BorderPane (Border Layout)
        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(hBoxTop);
        borderPane.setCenter(root);

        // Set up the Scene and Stage
        Scene scene = new Scene(borderPane, 400, 200);
        primaryStage.setTitle("JavaFX Pickers Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

