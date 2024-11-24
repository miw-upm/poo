package upm.fx;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableViewExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Crear la TableView
        TableView<User> table = new TableView<>();

        // Crear las columnas
        TableColumn<User, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

        // Añadir las columnas a la tabla
        table.getColumns().addAll(idColumn, nameColumn, emailColumn);

        // Crear datos y añadirlos a la tabla
        ObservableList<User> users = FXCollections.observableArrayList(
                new User("1", "Alice", "alice@example.com"),
                new User("2", "Bob", "bob@example.com"),
                new User("3", "Charlie", "charlie@example.com")
        );
        table.setItems(users);

        // Configurar el layout principal
        VBox root = new VBox(table);

        // Configurar la escena y mostrar la ventana
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("TableView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Clase de Usuario
    public static class User {
        private final String id;
        private final String name;
        private final String email;

        public User(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public String getId() {
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

