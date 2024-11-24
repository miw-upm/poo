package upm.fx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserFormDialog {

    public static User showUserForm(Stage ownerStage) {
        // Crear un escenario modal
        Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(ownerStage);
        dialog.setTitle("Crear Usuario");

        // Crear los elementos del formulario
        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField();
        nameField.setPromptText("Introduce el nombre");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Introduce el correo electrónico");

        Label ageLabel = new Label("Edad:");
        TextField ageField = new TextField();
        ageField.setPromptText("Introduce la edad");

        Button saveButton = new Button("Guardar");
        Button cancelButton = new Button("Cancelar");

        // Configuración del layout del formulario
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.addRow(0, nameLabel, nameField);
        grid.addRow(1, emailLabel, emailField);
        grid.addRow(2, ageLabel, ageField);
        grid.addRow(3, saveButton, cancelButton);

        // Acciones de los botones
        final User[] user = {null};

        saveButton.setOnAction(event -> {
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());
                user[0] = new User(name, email, age);
                dialog.close();
            } catch (NumberFormatException e) {
                System.out.println("Error: Edad debe ser un número válido.");
            }
        });

        cancelButton.setOnAction(event -> {
            dialog.close();
        });

        // Configuración de la escena del diálogo
        Scene dialogScene = new Scene(grid, 300, 200);
        dialog.setScene(dialogScene);

        // Mostrar el diálogo y bloquear hasta que se cierre
        dialog.showAndWait();

        return user[0];
    }
}
