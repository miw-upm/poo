package upm.appentrega4.gui.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upm.appentrega4.DependencyInjector;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.Controller;
import upm.appentrega4.gui.fx.components.Status;

public class GraphicalUserInterfaceFX extends Application {
    private Controller controller;
    private Menu commandMenu;
    private MenuItem logoutItem;
    private MenuItem loginItem;

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
        this.commandMenu = new Menu("Commands");
        menuBar.getMenus().addAll(this.prepareFileMenu(primaryStage), commandMenu,this.prepareHelpMenu());

        Label userLabel = new Label(this.controller.userName());
        this.controller.setListenerToUser( (String userName) -> {
            userLabel.setText(userName);
            generatedCommandMenu();
        });
        userLabel.setAlignment(Pos.CENTER_RIGHT);
        userLabel.setPadding(new Insets(10));
        HBox topBox = new HBox(menuBar, userLabel);
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setSpacing(10);
        HBox.setHgrow(menuBar, Priority.ALWAYS);
        topBox.setAlignment(Pos.CENTER);

        Status status = new Status();
        View.instance().setStatus(status);

        root.setTop(topBox);
        root.setCenter(contentArea);
        root.setBottom(status);

        this.generatedCommandMenu();

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Menu prepareFileMenu(Stage primaryStage) {
        Menu fileMenu = new Menu("File");
        loginItem = new MenuItem("login");
        loginItem.setOnAction(event -> this.controller.command("login").execute());
        fileMenu.getItems().add(loginItem);
        logoutItem = new MenuItem("Logout");
        logoutItem.setOnAction(event -> this.controller.command("logout").execute());
        fileMenu.getItems().add(logoutItem);
        MenuItem exitItem = new MenuItem("exit");
        exitItem.setOnAction(event -> primaryStage.close());
        fileMenu.getItems().add(exitItem);
        return fileMenu;
    }

    private Menu prepareHelpMenu() {
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("about");
        aboutItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("POO. Curso 2024-25");
            alert.showAndWait();
        });
        helpMenu.getItems().add(aboutItem);
        return helpMenu;
    }

    private void generatedCommandMenu() {
        this.commandMenu.getItems().clear();
        for (String key : this.controller.keys()) {
            MenuItem menuItem = new MenuItem(key);
            menuItem.setOnAction(this.menuActionHandler(key));
            this.commandMenu.getItems().addAll(menuItem);
        }
        boolean isLoggedIn = !Rol.NONE.equals(this.controller.userRol());
        this.loginItem.setDisable(isLoggedIn);
        this.logoutItem.setDisable(!isLoggedIn);
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
