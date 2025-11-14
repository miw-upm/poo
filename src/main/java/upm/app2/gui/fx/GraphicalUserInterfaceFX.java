package upm.app2.gui.fx;

import javafx.application.Application;
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
import java.util.function.Consumer;

public class GraphicalUserInterfaceFX extends Application {
    private static final String CSS_PATH = "/styles/app.css";
    private static final String CSS_ROOT_STYLE = "app-root";

    private static final String APP_TITLE = "UPM© Shop App";
    private static final String APP_HELP = "POO. Curso 2025-26";

    private static final double WINDOW_WIDTH = 600;
    private static final double WINDOW_HEIGHT = 400;

    private Status status;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(APP_TITLE);

        this.status = new Status();
        BorderPane root = new BorderPane();
        root.getStyleClass().add(CSS_ROOT_STYLE);
        root.setCenter(new VBox());
        root.setBottom(status);

        this.initializeApp(root, status);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(
                this.prepareFileMenu(primaryStage),
                this.prepareCommandMenu(),
                this.prepareHelpMenu()
        );
        root.setTop(menuBar);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH)).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeApp(BorderPane root, Status status) {
        GuiDependencyInjector.createInstance(root, status);
    }

    private Menu prepareFileMenu(Stage primaryStage) {
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event -> primaryStage.close());
        fileMenu.getItems().add(exitItem);
        return fileMenu;
    }

    private Menu prepareHelpMenu() {
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(APP_TITLE);
            alert.setHeaderText(APP_HELP);
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
                            .forEach(buildCommandConsumer(subMenu));
                    commandMenu.getItems().add(subMenu);
                });
        return commandMenu;
    }

    private Consumer<Command> buildCommandConsumer(Menu subMenu) {
        return cmd -> {
            MenuItem item = new MenuItem(cmd.name());
            item.setOnAction(e -> {
                try {
                    cmd.prepareAndExecute();
                } catch (Exception ex) {
                    this.status.error(ex.getMessage());
                }
            });
            subMenu.getItems().add(item);
        };
    }


}
