package upm.appfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CommandInterfaceFX extends Application {

    private final List<User> users = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Command Interface - Form Based");

        // Create the main layout
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

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

        // Button actions
        createUserButton.setOnAction(event -> {
            String mobile = mobileField.getText();
            String name = nameField.getText();
            String address = addressField.getText();

            if (mobile.isEmpty() || name.isEmpty() || address.isEmpty()) {
                outputArea.appendText("Error: All fields must be filled out.\n");
            } else {
                users.add(new User(Integer.parseInt(mobile), name, address));
                outputArea.appendText("User created successfully: " + name + "\n");

                // Clear fields
                mobileField.clear();
                nameField.clear();
                addressField.clear();
            }
        });

        listUsersButton.setOnAction(event -> {
            if (users.isEmpty()) {
                outputArea.appendText("No users available.\n");
            } else {
                outputArea.appendText("Listing all users:\n");
                users.forEach(user -> outputArea.appendText(user + "\n"));
            }
        });

        // Layout setup
        HBox buttonBox = new HBox(10, createUserButton, listUsersButton);
        VBox formBox = new VBox(10, mobileField, nameField, addressField, buttonBox);

        root.getChildren().addAll(titleLabel, formBox, outputArea);

        // Set the scene and show the stage
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // User class for demonstration
    public static class User {
        private final int mobile;
        private final String name;
        private final String address;

        public User(int mobile, String name, String address) {
            this.mobile = mobile;
            this.name = name;
            this.address = address;
        }

        @Override
        public String toString() {
            return "User{mobile=" + mobile + ", name='" + name + "', address='" + address + "'}";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
