package upm.fx;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class FX6AlertExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Crear un Alert de tipo información
        Alert infoAlert = new Alert(AlertType.INFORMATION);
        infoAlert.setTitle("Información");
        infoAlert.setHeaderText("Esto es un encabezado opcional");
        infoAlert.setContentText("Este es el contenido del mensaje.");
        infoAlert.showAndWait(); // Esperar hasta que el usuario cierre el cuadro

        // Crear un Alert de confirmación
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText("¿Estás seguro?");
        confirmAlert.setContentText("Elige tu opción:");

        // Manejar la respuesta del usuario
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.out.println("El usuario confirmó la acción.");
            } else {
                System.out.println("El usuario canceló la acción.");
            }
        });
        // Crear un Alert de tipo warning
        Alert warningAlert = new Alert(AlertType.WARNING);
        warningAlert.setTitle("Aviso");
        warningAlert.setHeaderText("Esto es un encabezado opcional");
        warningAlert.setContentText("Este es el contenido del mensaje.");
        warningAlert.showAndWait(); // Esperar hasta que el usuario cierre el cuadro

        // Crear un Alert de tipo error
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle("Aviso");
        errorAlert.setHeaderText("Esto es un encabezado opcional");
        errorAlert.setContentText("Este es el contenido del mensaje.");
        errorAlert.showAndWait(); // Esperar hasta que el usuario cierre el cuadro
    }
}
