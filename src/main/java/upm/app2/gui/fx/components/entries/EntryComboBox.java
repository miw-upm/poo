package upm.app2.gui.fx.components.entries;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.ComboBox;

import java.util.List;

public class EntryComboBox<T> extends ComboBox<KeyValue<T>> implements EntryField {
    public EntryComboBox(String prompt, List<KeyValue<T>> values) {
        super();
        this.getItems().addAll(values);
        this.setPromptText(prompt);
    }

    public BooleanBinding observableNotSelect() {
        return this.valueProperty().isNull();
    }

    @Override
    public String value() {
        KeyValue<T> selected = getValue();
        return (selected == null) ? "" : String.valueOf(selected.key());
    }

    @Override
    public void clear() {
        this.setValue(null);
    }
}