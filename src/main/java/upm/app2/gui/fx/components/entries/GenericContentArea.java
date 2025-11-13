package upm.app2.gui.fx.components.entries;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import upm.app2.gui.fx.components.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GenericContentArea extends VBox {
    private static final double LABEL_COLUMN_WIDTH = 90;
    private final List<Entry> entries = new ArrayList<>();
    private final GridPane form = new GridPane();

    public GenericContentArea(Consumer<List<String>> consumer, Status status) {
        getStyleClass().add("generic-content-area");
        form.getStyleClass().add("form-grid");
        setFillWidth(true);

        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPrefWidth(LABEL_COLUMN_WIDTH);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        this.form.getColumnConstraints().addAll(col0, col1);

        Button clear = new Button("Clear");
        clear.getStyleClass().addAll("form-button", "form-button-secondary");
        Button submit = new Button("Submit");
        submit.getStyleClass().addAll("form-button", "form-button-primary");

        submit.setOnAction(e -> {
            List<String> values = getValues();
            try {
                consumer.accept(values);
            } catch (Exception ex) {
                status.error(ex.getMessage());
            }
        });

        clear.setOnAction(e -> clearFields());

        HBox buttons = new HBox(submit, clear);
        buttons.getStyleClass().add("form-buttons");

        this.getChildren().addAll(this.form, buttons);
    }

    public void addTextFields(String... names) {
        for (String name : names) {
            addEntryField(name, new EntryTextField(name));
        }
    }

    public void addEntryField(String title, EntryField field) {
        int row = entries.size();
        form.add(new TitleLabel(title), 0, row);
        form.add((Node) field, 1, row);
        entries.add(new Entry(title, field));
    }

    private void clearFields() {
        entries.forEach(entry -> entry.field().clear());
    }

    public List<String> getValues() {
        return entries.stream()
                .map(entry -> entry.field().value())
                .toList();
    }

}
