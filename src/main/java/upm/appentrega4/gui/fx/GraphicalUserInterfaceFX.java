package upm.appentrega4.gui.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upm.appentrega4.DependencyInjector;
import upm.appentrega4.gui.Controller;
import upm.appentrega4.gui.fx.components.Status;

public class GraphicalUserInterfaceFX extends Application {
    private Controller controller;
    private Menu commandMenu;
    private MenuItem loginItem;
    private MenuItem logoutItem;

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

        VBox contentArea = new VBox();
        contentArea.setPadding(new Insets(10));
        contentArea.setSpacing(10);
        View.instance().setContentArea(contentArea);

        menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        this.loginItem = new MenuItem("login");
        this.loginItem.setOnAction(this.loginActionHandler());
        fileMenu.getItems().add(loginItem);
        this.logoutItem = new MenuItem("Logout");
        this.logoutItem.setOnAction(this.logoutActionHandler());
        this.logoutItem.setDisable(true);
        fileMenu.getItems().add(logoutItem);
        MenuItem exitItem = new MenuItem("exit");
        exitItem.setOnAction(event -> primaryStage.close());
        fileMenu.getItems().add(exitItem);

        this.commandMenu = new Menu("Commands");
        this.generatedCommandMenu();
        menuBar.getMenus().addAll(fileMenu, commandMenu);

        root.setTop(menuBar);
        root.setCenter(contentArea);

        VBox bottomArea = new VBox();
        Status status = new Status();
        this.userLabel = new Label(this.controller.userName());
        bottomArea.getChildren().add(userLabel);
        bottomArea.getChildren().add(status);
        root.setBottom(bottomArea);
        View.instance().setStatus(status);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generatedCommandMenu() {
        System.out.println(">>>>>>>"+"generatedCommandMenu...");
        this.commandMenu.getItems().clear();
        for (String key : this.controller.keys()) {
            MenuItem menuItem = new MenuItem(key);
            menuItem.setOnAction(this.menuActionHandler(key));
            this.commandMenu.getItems().addAll(menuItem);
        }
    }

    private EventHandler<ActionEvent> loginActionHandler() {
        return event -> {
            System.out.println(">>>>>>>"+"login...");
                this.controller.command("login").execute();
                this.loginItem.setDisable(true);
                this.logoutItem.setDisable(false);
                this.userLabel.setText(">>>"+this.controller.userName());
                this.generatedCommandMenu();
        };
    }

    private EventHandler<ActionEvent> logoutActionHandler() {
        return event -> {
            this.controller.command("logout").execute();
            this.loginItem.setDisable(false);
            this.logoutItem.setDisable(true);
            this.userLabel.setText(this.controller.userName());
            this.generatedCommandMenu();
        };
    }

    private EventHandler<ActionEvent> menuActionHandler(String item) {
        return eventSubmit -> {
            try {
                this.controller.command(item).execute();
            } catch (Exception e) {
                View.instance().getStatus().error(item + ": " + e.getMessage());
            }
        };
    }

}
