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

import java.lang.reflect.Field;
import java.util.List;

public class GenericTableViewExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Generic TableView Example");

        // Crear la TableView
        TableView<Object> tableView = new TableView<>();

        // Crear datos genéricos
        List<Person> people = List.of(
                new Person(1, "Alice", "alice@example.com"),
                new Person(2, "Bob", "bob@example.com"),
                new Person(3, "Charlie", "charlie@example.com")
        );

        ObservableList<Object> data = FXCollections.observableArrayList(people);

        // Generar columnas dinámicamente
        generateColumns(tableView, Person.class);

        // Asignar los datos a la tabla
        tableView.setItems(data);

        // Crear el layout
        VBox root = new VBox(tableView);

        // Configurar la escena
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Genera columnas dinámicamente basadas en los campos de la clase proporcionada.
     *
     * @param tableView la tabla donde se añadirán las columnas.
     * @param clazz     la clase de los objetos que se mostrarán en la tabla.
     */
    private void generateColumns(TableView<Object> tableView, Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            TableColumn<Object, String> column = new TableColumn<>(capitalize(field.getName()));

            // Usar reflexión para extraer los valores de las propiedades
            column.setCellValueFactory(data -> {
                try {
                    field.setAccessible(true);
                    Object value = field.get(data.getValue());
                    return new SimpleStringProperty(value != null ? value.toString() : "");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return new SimpleStringProperty("");
                }
            });

            tableView.getColumns().add(column);
        }
    }

    /**
     * Capitaliza la primera letra de un texto.
     *
     * @param text el texto a capitalizar.
     * @return el texto capitalizado.
     */
    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    /**
     * Clase de ejemplo para llenar la tabla.
     */
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

    public static void main(String[] args) {
        launch(args);
    }
}
