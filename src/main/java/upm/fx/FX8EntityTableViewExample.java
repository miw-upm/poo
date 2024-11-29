package upm.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upm.appentrega4.data.models.Entity;
import upm.appentrega4.gui.fx.components.EntityTableView;

import java.util.List;

public class FX8EntityTableViewExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Generic TableView");

        // Crear la TableView


        // Crear datos genéricos
        List<Object> people = List.of(
                new Person("Alice", "alice@example.com"),
                new Person("Bob", "bob@example.com"),
                new Person("Charlie", "charlie@example.com")
        );
        EntityTableView tableView = new EntityTableView(people);

        // Crear el layout
        VBox root = new VBox(tableView);

        // Configurar la escena
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class Person extends Entity {
        private final String name;
        private final String email;

        public Person(String name, String email) {
            super.setId(666);
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}
