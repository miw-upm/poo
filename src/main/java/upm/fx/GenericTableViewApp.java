package upm.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upm.appentrega4.gui.fx.components.GenericTableView;

import java.util.List;

public class GenericTableViewApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Generic TableView");

        // Crear la TableView


        // Crear datos genéricos
        List<Object> people = List.of(
                new Person(1, "Alice", "alice@example.com"),
                new Person(2, "Bob", "bob@example.com"),
                new Person(3, "Charlie", "charlie@example.com")
        );
        GenericTableView tableView = new GenericTableView(people);

        // Crear el layout
        VBox root = new VBox(tableView);

        // Configurar la escena
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class Person {
        private final int id;
        private final String name;
        private final String email;

        public Person(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}
