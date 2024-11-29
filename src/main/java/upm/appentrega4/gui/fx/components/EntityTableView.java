package upm.appentrega4.gui.fx.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Method;
import java.util.List;

public class EntityTableView extends TableView<Object> {

    public EntityTableView(List<Object> content) {
        ObservableList<Object> data = FXCollections.observableArrayList(content);
        this.addColumns(content.get(0).getClass().getSuperclass());
        this.addColumns(content.get(0).getClass());
        this.setItems(data);
    }


    private void addColumns(Class<?> clazz) {
        if (clazz == null) return;

        for (Method method : clazz.getDeclaredMethods()) {
            // Consideramos sólo los getters
            if (isGetter(method)) {
                String fieldName = extractFieldName(method);

                TableColumn<Object, String> column = new TableColumn<>(fieldName);
                column.setCellValueFactory(item -> {
                    try {
                        Object value = method.invoke(item.getValue());
                        return new SimpleStringProperty(value != null ? value.toString() : "");
                    } catch (Exception exception) {
                        LogManager.getLogger(this.getClass()).error(exception::getMessage);
                        return new SimpleStringProperty("");
                    }
                });

                this.getColumns().add(column);
            }
        }
    }

    private boolean isGetter(Method method) {
        return method.getName().startsWith("get") &&
                method.getParameterCount() == 0 &&
                !method.getReturnType().equals(void.class);
    }

    private String extractFieldName(Method getter) {
        // Convierte el nombre del getter en el nombre del campo (por ejemplo, getName -> name)
        String name = getter.getName().substring(3); // Elimina "get"
        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }
}