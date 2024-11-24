package upm.fx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ComboBoxExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ComboBox Example");

        // Crear un ComboBox con opciones
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Option 1", "Option 2", "Option 3", "Option 4");

        // Seleccionar una opción por defecto
        comboBox.setValue("Option 1");

        // Evento para manejar cambios en la selección
        comboBox.setOnAction(event -> {
            String selectedOption = comboBox.getValue();
            System.out.println("Selected: " + selectedOption);
        });

        // Layout
        VBox root = new VBox(10);
        root.getChildren().add(comboBox);

        // Crear la escena
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
