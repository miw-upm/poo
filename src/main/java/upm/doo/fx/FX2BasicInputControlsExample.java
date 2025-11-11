package upm.doo.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FX2BasicInputControlsExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // TextField for entering username
        Label nameLabel = new Label("Username:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your username");

        // PasswordField for entering password
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        // CheckBox
        CheckBox rememberMeCheckBox = new CheckBox("Remember Me");

        // RadioButtons
        Label genderLabel = new Label("Gender:");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);

        // Button
        Button submitButton = new Button("Submit");

        // Event handling for the button
        submitButton.setOnAction(event -> {  //Patrón Observer, versión con Lambda
            String username = nameField.getText();
            String password = passwordField.getText();
            boolean rememberMe = rememberMeCheckBox.isSelected();
            Toggle selectedToggle = genderGroup.getSelectedToggle();
            String gender = "";
            if (selectedToggle != null) {
                gender = ((RadioButton) selectedToggle).getText();
            } else {
                gender = "No seleccionado";
            }
            // Display the input in a dialog box
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Input");
            alert.setHeaderText("Form Submitted!");
            alert.setContentText("Username: " + username + "\nPassword: " + password
                    + "\nRemember Me: " + rememberMe + "\nGender: " + gender);
            alert.setOnShown(e -> {
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.setX(primaryStage.getX() + 200);
                alertStage.setY(primaryStage.getY() + 150);
            });
            alert.showAndWait();
        });

        // Adding components to the grid
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(rememberMeCheckBox, 1, 2);
        gridPane.add(genderLabel, 0, 3);
        gridPane.add(maleRadio, 1, 3);
        gridPane.add(femaleRadio, 1, 4);
        gridPane.add(submitButton, 1, 5);

        // Create a scene and display the stage
        Scene scene = new Scene(gridPane, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Basic Input Controls");
        primaryStage.show();
    }
}

