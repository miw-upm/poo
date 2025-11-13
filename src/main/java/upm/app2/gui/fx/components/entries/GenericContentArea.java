package upm.app2.gui.fx.components.entries;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import upm.app2.gui.fx.components.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GenericContentArea extends VBox {
    private static final double ROOT_MARGIN = 12;
    private static final double ROOT_GAP = 8;
    private static final double LABEL_COLUMN_WIDTH = 90;
    private static final double BUTTON_SPACING = 8;
    private static final Insets BUTTONS_PADDING = new Insets(5, 0, 0, 0);
    private final List<Entry> entries = new ArrayList<>();
    private final GridPane form = new GridPane();

    public GenericContentArea(Consumer<List<String>> consumer, Status status) {
        setSpacing(ROOT_MARGIN);
        setPadding(new Insets(ROOT_MARGIN));
        setFillWidth(true);
        this.form.setHgap(ROOT_GAP);
        this.form.setVgap(ROOT_GAP);

        ColumnConstraints constraints0 = new ColumnConstraints();
        constraints0.setPrefWidth(LABEL_COLUMN_WIDTH);
        ColumnConstraints constraints1 = new ColumnConstraints();
        constraints1.setHgrow(Priority.ALWAYS);
        this.form.getColumnConstraints().addAll(constraints0, constraints1);

        Button clear = new Button("Clear");
        Button submit = new Button("Submit");

        submit.setOnAction(e -> {
            List<String> values = getValues();
            try {
                consumer.accept(values);
            } catch (Exception ex) {
                status.error(ex.getMessage());
            }
        });

        clear.setOnAction(e -> clearFields());

        HBox buttons = new HBox(BUTTON_SPACING, submit, clear);
        buttons.setPadding(BUTTONS_PADDING);

        this.getChildren().addAll(this.form, buttons);
    }

    public void addTextFields(List<String> names) {
        for (String name : names) {
            addEntryField(name, new EntryTextField(name));
        }
    }

    public void addTextFields(String... names) {
        for (String name : names) {
            addEntryField(name, new EntryTextField(name));
        }
    }

    public void addEntryField(String title, EntryField field) {
        Label label = new TitleLabel(title);
        Node control = (Node) field;

        int row = entries.size();
        form.add(label, 0, row);
        form.add(control, 1, row);

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
