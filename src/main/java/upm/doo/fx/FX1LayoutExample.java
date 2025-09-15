package upm.doo.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FX1LayoutExample extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ejemplo de Layouts en JavaFX");

        // HBox (Horizontal Layout)
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(new Button("Botón 1"), new Button("Botón 2"), new Button("Botón 3"), new Button("Botón 4"));

        // VBox (Vertical Layout)
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(new Label("Etiqueta 1"), new Label("Etiqueta 2"), new Label("Etiqueta 3"), new Label("Etiqueta 4"));

        // GridPane (Grid Layout)
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(30));
        gridPane.add(new Label("Etiqueta 1,1"), 0, 0);
        gridPane.add(new Label("Etiqueta 2,1"), 0, 1);
        gridPane.add(new Label("Etiqueta 3,1"), 0, 0);
        gridPane.add(new Button("Botón 1,2"), 1, 0);
        gridPane.add(new Button("Botón 2,2"), 1, 1);
        gridPane.add(new Button("Botón 3,2"), 1, 0);


        // StackPane (Overlapping Layout)
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(new Label("Etiqueta en el fondo"), new Button("Botón encima"));

        // BorderPane (Border Layout)
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hbox);
        borderPane.setBottom(stackPane);
        borderPane.setLeft(vbox);
        borderPane.setRight(new Button("Right"));
        borderPane.setCenter(gridPane);

        // Configuración de la escena
        Scene scene = new Scene(borderPane, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

