package upm.appentrega4.gui.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FX5AdvancedControlsExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Components Example");

        // ---- TabPane ----
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Tab 1", new Label("Content for Tab 1"));
        Tab tab2 = new Tab("Tab 2", new Label("Content for Tab 2"));
        tabPane.getTabs().addAll(tab1, tab2);

        // ---- Accordion ----
        Accordion accordion = new Accordion();
        TitledPane pane1 = new TitledPane("Pane 1", new Label("Details for Pane 1"));
        TitledPane pane2 = new TitledPane("Pane 2", new Label("Details for Pane 2"));
        accordion.getPanes().addAll(pane1, pane2);

        // ---- Slider ----
        Slider slider = new Slider(0, 100, 50); // Min 0, Max 100, Initial 50
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        // ---- ProgressBar and ProgressIndicator ----
        ProgressBar progressBar = new ProgressBar(0.5); // 50% completed
        ProgressIndicator progressIndicator = new ProgressIndicator(0.5); // 50% completed

        // ---- Spinner ----
        Spinner<Integer> spinner = new Spinner<>(1, 10, 1); // Min 1, Max 10, Initial 1

        // ---- Pagination ----
        Pagination pagination = new Pagination(5, 0); // 5 pages, start at page 0
        pagination.setPageFactory(pageIndex -> new Label("Page " + (pageIndex + 1)));

        // ---- Layout ----
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(
                new Label("TabPane:"), tabPane,
                new Label("Accordion:"), accordion,
                new Label("Slider:"), slider,
                new Label("ProgressBar and ProgressIndicator:"), progressBar, progressIndicator,
                new Label("Spinner:"), spinner,
                new Label("Pagination:"), pagination
        );

        // ---- Scene and Stage ----
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
