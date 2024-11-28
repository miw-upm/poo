package upm.fx;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXComponentsExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Main Layout
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        // ComboBox
        Label comboBoxLabel = new Label("Select an Option (ComboBox):");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Option 1", "Option 2", "Option 3");
        comboBox.setValue("Option 1"); // Default value

        // ChoiceBox
        Label choiceBoxLabel = new Label("Select an Option (ChoiceBox):");
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Choice A", "Choice B", "Choice C");
        choiceBox.setValue("Choice A"); // Default value

        // ListView
        Label listViewLabel = new Label("Select an Item (ListView):");
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll("Item 1", "Item 2", "Item 3", "Item 4");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // TableView
        Label tableViewLabel = new Label("View Table Data:");
        TableView<Person> tableView = new TableView<>();
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        tableView.getColumns().addAll(firstNameCol, lastNameCol);
        tableView.getItems().addAll(
                new Person("John", "Doe"),
                new Person("Jane", "Smith"),
                new Person("Emily", "Jones")
        );

        // TreeView
        Label treeViewLabel = new Label("Tree Structure:");
        TreeItem<String> rootNode = new TreeItem<>("Root");
        rootNode.setExpanded(true);
        TreeItem<String> branch1 = new TreeItem<>("Branch 1");
        branch1.getChildren().addAll(new TreeItem<>("Leaf 1.1"), new TreeItem<>("Leaf 1.2"));
        TreeItem<String> branch2 = new TreeItem<>("Branch 2");
        branch2.getChildren().addAll(new TreeItem<>("Leaf 2.1"), new TreeItem<>("Leaf 2.2"));
        rootNode.getChildren().addAll(branch1, branch2);
        TreeView<String> treeView = new TreeView<>(rootNode);

        // Add Components to the Layout
        root.getChildren().addAll(
                comboBoxLabel, comboBox,
                choiceBoxLabel, choiceBox,
                listViewLabel, listView,
                tableViewLabel, tableView,
                treeViewLabel, treeView
        );

        // Scene and Stage
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("JavaFX Components Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Person class for TableView
    public static class Person {
        private final String firstName;
        private final String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }
}

