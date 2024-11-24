package upm.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserDialogApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Dialog Example");

        // Botón para abrir el diálogo
        Button openDialogButton = new Button("Crear Usuario");
        openDialogButton.setOnAction(event -> {
            User user = UserFormDialog.showUserForm(primaryStage);
            if (user != null) {
                System.out.println("Usuario creado: " + user);
            } else {
                System.out.println("No se creó ningún usuario.");
            }
        });

        // Configurar el layout principal
        VBox root = new VBox(10, openDialogButton);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Configurar la escena principal
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
