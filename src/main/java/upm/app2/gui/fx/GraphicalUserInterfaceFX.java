package upm.app2.gui.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upm.app2.gui.Command;
import upm.app2.gui.GuiDependencyInjector;
import upm.app2.gui.fx.components.Status;

import java.util.Comparator;
import java.util.Objects;

public class GraphicalUserInterfaceFX extends Application {
    private static final double WINDOW_WIDTH = 600;
    private static final double WINDOW_HEIGHT = 400;
    private Status status;
    private BorderPane root;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        GuiDependencyInjector.createInstance(this);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("UPM© Shop App");

        this.root = new BorderPane();
        this.root.getStyleClass().add("app-root");

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(
                this.prepareFileMenu(primaryStage),
                this.prepareCommandMenu(),
                this.prepareHelpMenu()
        );
        this.root.setTop(menuBar);

        this.setContentArea(new VBox());

        this.status = new Status();
        this.root.setBottom(status);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/app.css")).toExternalForm()
        );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Menu prepareFileMenu(Stage primaryStage) {
        Menu fileMenu = new Menu("File");
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
            alert.setTitle("UPM©");
            alert.setHeaderText("POO. Curso 2025-26");
            alert.showAndWait();
        });
        helpMenu.getItems().add(aboutItem);
        return helpMenu;
    }

    private Menu prepareCommandMenu() {
        Menu commandMenu = new Menu("Commands");
        GuiDependencyInjector.getInstance().getCommandsByGroup().keySet().stream()
                .sorted()
                .forEach(group -> {
                    Menu subMenu = new Menu(group);
                    GuiDependencyInjector.getInstance().getCommandsByGroup().get(group).stream()
                            .sorted(Comparator.comparing(Command::name))
                            .forEach(cmd -> {
                                MenuItem item = new MenuItem(cmd.name());
                                item.setOnAction(e -> cmd.prepareAndExecute());
                                subMenu.getItems().add(item);
                            });
                    commandMenu.getItems().add(subMenu);
                });
        return commandMenu;
    }

    public void setContentArea(VBox vBox) {
        vBox.getStyleClass().add("content-area");
        this.root.setCenter(vBox);
    }


    public Status getStatus() {
        return this.status;
    }
}
