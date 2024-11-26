package upm.appentrega4.gui.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upm.appentrega4.DependencyInjector;
import upm.appentrega4.gui.Controller;
import upm.appentrega4.gui.fx.components.Status;
import upm.appentrega4.gui.fx.dialogs.ObjectListDialog;

import java.util.ArrayList;
import java.util.List;

public class GraphicalUserInterfaceFX extends Application {
    private Controller controller;
    private Menu commandMenu;
    private VBox contentArea;
    private Status status;

    private Label userLabel;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        this.controller = DependencyInjector.getInstance().getController();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("App Shop");
        MenuBar menuBar;

        BorderPane root = new BorderPane();
        this.contentArea = new VBox();

        menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem loginItem = new MenuItem("login");
        loginItem.setOnAction(this.menuActionHandler("login"));
        fileMenu.getItems().add(loginItem);
        MenuItem logoutItem = new MenuItem("Logout");
        logoutItem.setOnAction(this.menuActionHandler("logout"));
        logoutItem.setDisable(true);
        fileMenu.getItems().add(logoutItem);
        MenuItem exitItem = new MenuItem("exit");
        exitItem.setOnAction(event -> primaryStage.close());
        fileMenu.getItems().add(exitItem);

        this.commandMenu = new Menu("Command");
        this.generatedCommandMenu();

        menuBar.getMenus().addAll(fileMenu, commandMenu);

        contentArea.setPadding(new Insets(10));
        contentArea.setSpacing(10);

        root.setTop(menuBar);
        root.setCenter(contentArea);

        VBox bottomArea = new VBox();
        this.status = new Status();
        this.userLabel = new Label(this.controller.userName());
        bottomArea.getChildren().add(userLabel);
        bottomArea.getChildren().add(status);
        root.setBottom(bottomArea);

        this.controller.setStatus(this.status);
        this.controller.setContentArea(this.contentArea);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generatedCommandMenu(String item) {
        if ("login".equals(item) || "logout".equals(item)) {
            this.userLabel.setText(this.controller.userName());
            generatedCommandMenu();
        }
    }

    private void generatedCommandMenu() {
        this.commandMenu.getItems().clear();
        for (String key : this.controller.keys()) {
            MenuItem menuItem = new MenuItem(key);
            menuItem.setOnAction(this.menuActionHandler(key));
            this.commandMenu.getItems().addAll(menuItem);
        }
    }

    private EventHandler<ActionEvent> menuActionHandler(String item) {
        if (this.controller.command(item).params().isEmpty()) {
            return eventSubmit -> {
                new ObjectListDialog(item, this.controller.command(item).execute(new String[0]));
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
                new ObjectListDialog(item, this.controller.command(item).execute(
                        fields.stream().map(TextInputControl::getText).toArray(String[]::new)));
                this.generatedCommandMenu(item);
            } catch (Exception e) {
                this.status.error(item + ": " + e.getMessage());
            }
        };
    }
}
