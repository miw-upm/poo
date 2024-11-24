package upm.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalDialogExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ejemplo de Diálogos Modales");

        // Botón para abrir el diálogo modal
        Button openDialogButton = new Button("Abrir Diálogo Modal");
        openDialogButton.setOnAction(event -> openModalDialog(primaryStage));

        // Layout principal
        VBox root = new VBox(10, openDialogButton);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Configuración de la escena principal
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openModalDialog(Stage ownerStage) {
        // Crear un nuevo escenario para el diálogo modal
        Stage dialog = new Stage();
        dialog.setTitle("Diálogo Modal");

        // Configurar el escenario como modal
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(ownerStage);

        // Contenido del diálogo
        Label message = new Label("Este es un diálogo modal.");
        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(event -> dialog.close());

        // Layout del diálogo
        VBox dialogVBox = new VBox(10, message, closeButton);
        dialogVBox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Configuración de la escena del diálogo
        Scene dialogScene = new Scene(dialogVBox, 250, 150);
        dialog.setScene(dialogScene);

        // Mostrar el diálogo
        dialog.showAndWait(); // Bloquea la interacción con la ventana principal
    }

    public static void main(String[] args) {
        launch(args);
    }
}

