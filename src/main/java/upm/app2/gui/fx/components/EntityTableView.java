package upm.app2.gui.fx.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EntityTableView<T> extends TableView<T> {
    private static final String IS = "is";
    private static final String GET = "get";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public EntityTableView(List<T> content) {
        if (content == null || content.isEmpty()) {
            return;
        }
        Class<?> clazz = content.getFirst().getClass();
        this.addColumns(clazz.getSuperclass());
        this.addColumns(clazz);
        this.setItems(FXCollections.observableArrayList(content));

    }

    private void addColumns(Class<?> clazz) {
        if (clazz == null) {
            return;
        }
        for (Method method : clazz.getDeclaredMethods()) {
            if (isGetter(method)) {
                String fieldName = extractFieldName(method);
                TableColumn<T, String> column = new TableColumn<>(beautify(fieldName));
                column.setCellValueFactory(item -> {
                    try {
                        Object value = method.invoke(item.getValue());
                        return new SimpleStringProperty(formatValue(value));
                    } catch (Exception exception) {
                        LogManager.getLogger(this.getClass()).error(exception::getMessage);
                        return new SimpleStringProperty("Error");
                    }
                });
                this.getColumns().add(column);
            }
        }
    }

    private boolean isGetter(Method method) {
        boolean isGetter = method.getName().startsWith(GET) && !method.getReturnType().equals(void.class);
        boolean isBooleanGetter = method.getName().startsWith(IS) &&
                (method.getReturnType().equals(boolean.class) || method.getReturnType().equals(Boolean.class));
        return method.getParameterCount() == 0 && (isGetter || isBooleanGetter);
    }

    private String extractFieldName(Method getter) {
        if (getter.getName().startsWith(IS)) {
            return getter.getName().substring(IS.length());
        } else {
            return getter.getName().substring(GET.length());
        }
    }

    private String beautify(String name) {
        return name
                .replaceAll("([A-Z])", " $1")
                .trim()
                .replaceFirst("^.", String.valueOf(Character.toUpperCase(name.charAt(0))));
    }

    private String formatValue(Object value) {
        return switch (value) {
            case null -> "";
            case LocalDate date -> DATE_FORMAT.format(date);
            case LocalDateTime dateTime -> DATE_TIME_FORMAT.format(dateTime);
            case Instant instant -> DATE_TIME_FORMAT.format(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
            case java.util.Date dateOld -> DATE_TIME_FORMAT.format(dateOld.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime());
            default -> value.toString();
        };

    }
}