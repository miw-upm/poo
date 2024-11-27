package upm.appentrega4.gui.fx.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.lang.reflect.Field;
import java.util.List;

public class EntityTableView extends TableView<Object> {

    public EntityTableView(List<Object> content) {
        ObservableList<Object> data = FXCollections.observableArrayList(content);
        this.addColumns(content.get(0).getClass().getSuperclass());
        this.addColumns(content.get(0).getClass());
        this.setItems(data);
    }


    private void addColumns(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            TableColumn<Object, String> column = new TableColumn<>(field.getName());
            // Usar reflexión para extraer los valores de las propiedades
            column.setCellValueFactory(item -> {
                try {
                    field.setAccessible(true);
                    Object value = field.get(item.getValue());
                    return new SimpleStringProperty(value != null ? value.toString() : "");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return new SimpleStringProperty("");
                }
            });
            this.getColumns().add(column);
        }
    }

}
