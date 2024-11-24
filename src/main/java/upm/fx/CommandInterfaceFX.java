package upm.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommandInterfaceFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("App Shop");

        // Create a MenuBar
        MenuBar menuBar = new MenuBar();

        // Create "File" menu
        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");

        // Add menu items to the "File" menu
        fileMenu.getItems().addAll(newItem, openItem, saveItem, new SeparatorMenuItem(), exitItem);

        // Create "Help" menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");

        // Add menu items to the "Help" menu
        helpMenu.getItems().add(aboutItem);

        aboutItem.setOnAction(actionEvent -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Dialog Header");
                    alert.setContentText("This is an example of a dialog in JavaFX.");

                    // Mostrar el diálogo
                    alert.showAndWait();
                }
        );

        // Add all menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        // Set up an event handler for "Exit"
        exitItem.setOnAction(event -> {
            System.out.println("Exiting application...");
            primaryStage.close();
        });

        // Create the main layout
        BorderPane root = new BorderPane();
        root.setTop(menuBar);

        // Create the content area
        VBox contentArea = new VBox();
        contentArea.setPadding(new Insets(10));
        contentArea.setSpacing(10);

        // Title label
        Label titleLabel = new Label("User Management");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Form fields
        TextField mobileField = new TextField();
        mobileField.setPromptText("Enter Mobile Number");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name");

        TextField addressField = new TextField();
        addressField.setPromptText("Enter Address");

        // Buttons
        Button createUserButton = new Button("Create User");
        Button listUsersButton = new Button("List Users");

        // Output area
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(200);

        // Button functionality
        createUserButton.setOnAction(event -> {
            String mobile = mobileField.getText();
            String name = nameField.getText();
            String address = addressField.getText();

            if (mobile.isEmpty() || name.isEmpty() || address.isEmpty()) {
                outputArea.appendText("Error: All fields are required.\n");
            } else {
                outputArea.appendText("User Created: " + name + ", " + mobile + ", " + address + "\n");
                mobileField.clear();
                nameField.clear();
                addressField.clear();
            }
        });

        listUsersButton.setOnAction(event -> outputArea.appendText("Listing users (Feature not implemented yet).\n"));

        // Layout setup
        HBox buttonBox = new HBox(10, createUserButton, listUsersButton);
        VBox formBox = new VBox(10, mobileField, nameField, addressField, buttonBox);

        // Add components to the content area
        contentArea.getChildren().addAll(titleLabel, formBox, outputArea);

        // Add content area to the center of the root layout
        root.setCenter(contentArea);

        // Set the scene and show the stage
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
