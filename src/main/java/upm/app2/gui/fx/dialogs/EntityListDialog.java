package upm.app2.gui.fx.dialogs;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import upm.app2.gui.fx.components.EntityTableView;

import java.util.List;
import java.util.Set;

public class EntityListDialog extends Alert {
    private static final Set<Class<?>> WRAPPERS = Set.of(
            Boolean.class, Byte.class, Short.class, Integer.class, Long.class,
            Float.class, Double.class, Character.class, String.class
    );
    private static final double LIST_VIEW_WIDTH = 600;
    private static final double LIST_VIEW_HEIGHT = 300;
    private static final double TABLE_DIALOG_WIDTH = 800;
    private static final double TABLE_DIALOG_HEIGHT = 500;
    private static final double TABLE_STAGE_WIDTH = TABLE_DIALOG_WIDTH + 50;
    private static final double TABLE_STAGE_HEIGHT = TABLE_DIALOG_HEIGHT + 50;

    public <T> EntityListDialog(String header, List<T> content) {
        super(AlertType.INFORMATION);
        getDialogPane().getStyleClass().add("entity-dialog");
        this.setTitle("Information");
        this.setHeaderText(header);
        if (content == null || content.isEmpty()) {
            this.getDialogPane().setContent(new Label("No existen resultados!!!"));
        } else if (isWrapperOrSimple(content.getFirst().getClass())) {
            ListView<String> listView = new ListView<>(
                    FXCollections.observableArrayList(
                            content.stream().map(String::valueOf).toList()
                    )
            );
            listView.setPrefWidth(LIST_VIEW_WIDTH);
            listView.setPrefHeight(LIST_VIEW_HEIGHT);
            getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            getDialogPane().setContent(listView);
        } else {
            EntityTableView<T> entityTableView = new EntityTableView<>(content);
            this.getDialogPane().setContent(entityTableView);
            this.getDialogPane().setPrefWidth(TABLE_DIALOG_WIDTH);
            this.getDialogPane().setPrefHeight(TABLE_DIALOG_HEIGHT);
            Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
            stage.setWidth(TABLE_STAGE_WIDTH);
            stage.setHeight(TABLE_STAGE_HEIGHT);
        }
        this.showAndWait();
    }

    private static boolean isWrapperOrSimple(Class<?> clazz) {
        return WRAPPERS.contains(clazz);
    }
}
