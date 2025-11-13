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

    public <T> EntityListDialog(String header, List<T> content) {
        super(AlertType.INFORMATION);
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
            listView.setPrefWidth(600);
            listView.setPrefHeight(300);
            getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            getDialogPane().setContent(listView);
        } else {
            EntityTableView<T> entityTableView = new EntityTableView<>(content);
            this.getDialogPane().setContent(entityTableView);
            this.getDialogPane().setPrefWidth(800);
            this.getDialogPane().setPrefHeight(500);
            Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
            stage.setWidth(850);
            stage.setHeight(550);
        }
        this.showAndWait();
    }

    private static boolean isWrapperOrSimple(Class<?> clazz) {
        return WRAPPERS.contains(clazz);
    }
}
