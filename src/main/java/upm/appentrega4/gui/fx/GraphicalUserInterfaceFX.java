package upm.appentrega4.gui.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upm.appentrega4.DependencyInjector;
import upm.appentrega4.gui.Controller;

import java.util.ArrayList;
import java.util.List;

public class GraphicalUserInterfaceFX extends Application {
    private Controller controller;
    private Menu commandMenu;
    private VBox contentArea;
    private Stage primaryStage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        this.controller = DependencyInjector.getInstance().getController();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        MenuBar menuBar;

        BorderPane root = new BorderPane();
        this.contentArea = new VBox();

        menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event -> primaryStage.close());
        fileMenu.getItems().add(exitItem);

        this.commandMenu = new Menu("Command");
        this.generatedCommandMenu();

        menuBar.getMenus().addAll(fileMenu, commandMenu);

        contentArea.setPadding(new Insets(10));
        contentArea.setSpacing(10);

        root.setTop(menuBar);
        root.setCenter(contentArea);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generatedCommandMenu(String item) {
        if ("login".equals(item) || "logout".equals(item)) {
            generatedCommandMenu();
        }
    }

    private void generatedCommandMenu() {
        this.primaryStage.setTitle("App Shop. " + this.controller.userName());
        this.commandMenu.getItems().clear();
        for (String key : this.controller.keys()) {
            MenuItem menuItem = new MenuItem(key);
            menuItem.setOnAction(menuActionHandler(key));
            this.commandMenu.getItems().addAll(menuItem);
        }
    }

    private EventHandler<ActionEvent> menuActionHandler(String item) {
        if (this.controller.command(item).params().isEmpty()) {
            return eventSubmit -> {
                new ListDialog(item, this.controller.command(item).execute(new String[0]));
                this.generatedCommandMenu(item);
            };
        } else {
            return event -> this.preparedForm(contentArea, item);
        }
    }

    private void preparedForm(VBox contentArea, String item) {
        contentArea.getChildren().clear();
        List<TextField> fields = new ArrayList<>();
        for (String fieldName : this.controller.command(item).params()) {
            TextField field = new TextField();
            field.setPromptText("Enter " + fieldName);
            fields.add(field);
            contentArea.getChildren().add(field);
        }
        Button submit = new Button("submit");
        submit.setOnAction(submitActionHandler(fields, item));
        contentArea.getChildren().add(submit);
    }

    private EventHandler<ActionEvent> submitActionHandler(List<TextField> fields, String item) {
        return eventSubmit -> {
            try {
                new ListDialog(item, this.controller.command(item).execute(
                        fields.stream().map(TextInputControl::getText).toArray(String[]::new)));
                this.generatedCommandMenu(item);
            } catch (Exception e) {
                new ErrorDialog(item, e.getMessage());
            }
        };
    }
}
