package upm.doo.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        HBox hboxTop = new HBox(10);
        hboxTop.setPadding(new Insets(10));
        hboxTop.setAlignment(Pos.CENTER);
        hboxTop.setStyle("-fx-background-color: #546E7A;");
        hboxTop.getChildren().addAll(new Button("BorderPane-Top"), new Button("HBox"), new Button("Top 3"), new Button("Top 4"));

        // VBox (Vertical Layout)
        VBox vboxLeft = new VBox(1);
        vboxLeft.setStyle("-fx-background-color: #90A4AE;");
        vboxLeft.getChildren().addAll(new Button("VBox"), new Button("Left 2"), new Button("Left 3"), new Button("Left 4"));

        // VBox (Vertical Layout)
        VBox vboxRight = new VBox(1);
        vboxRight.setStyle("-fx-background-color: #90A4AE;");
        vboxRight.getChildren().addAll(new Button("VBox"), new Button("Right 2"), new Button("Right 3"), new Button("Right 4"));

        // GridPane (Grid Layout)
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.setStyle("-fx-background-color: #ECEFF1;");
        gridPane.add(new Label("GridPane"), 0, 0);
        gridPane.add(new Label("Label 2,1"), 0, 1);
        gridPane.add(new Label("Label 3,1"), 0, 2);
        gridPane.add(new Button("Button 1,2"), 1, 0);
        gridPane.add(new Button("Button 2,2"), 1, 1);
        gridPane.add(new Button("Button 3,2"), 1, 2);


        // StackPane (Overlapping Layout)
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #37474F;");
        Label label = new Label("BorderPane-Button StackPane...");
        label.setStyle("-fx-text-fill: white;");
        stackPane.getChildren().add(label);

        // BorderPane (Border Layout)
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hboxTop);
        borderPane.setBottom(stackPane);
        borderPane.setLeft(vboxLeft);
        borderPane.setRight(vboxRight);
        borderPane.setCenter(gridPane);

        // Configuración de la escena
        Scene scene = new Scene(borderPane, 400, 600); // Scene: contenido visual de la ventana
        primaryStage.setScene(scene);  // primaryStage: ventana principal de la app
        primaryStage.show();
    }
}

