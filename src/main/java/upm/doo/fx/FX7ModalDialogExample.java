package upm.doo.fx;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;

import java.util.Optional;

public class FX7ModalDialogExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Crear un Dialog
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Crear Usuario");
        dialog.setHeaderText("Introduce la información del usuario");

        // Añadir botones OK y Cancel
        ButtonType okButtonType = new ButtonType("Crear", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        // Crear un formulario dentro del diálogo
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameField = new TextField();
        nameField.setPromptText("Nombre");

        TextField emailField = new TextField();
        emailField.setPromptText("Correo electrónico");

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Correo:"), 0, 1);
        grid.add(emailField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Hacer el botón OK deshabilitado si los campos están vacíos
        dialog.getDialogPane().lookupButton(okButtonType).setDisable(true);

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            dialog.getDialogPane().lookupButton(okButtonType).setDisable(
                    nameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty()
            );
        });

        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            dialog.getDialogPane().lookupButton(okButtonType).setDisable(
                    nameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty()
            );
        });

        // Capturar la respuesta del diálogo
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return "Nombre: " + nameField.getText() + ", Correo: " + emailField.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(LogManager.getLogger(this.getClass())::info); // Imprimir el resultado si existe
    }
}




