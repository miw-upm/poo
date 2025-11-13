package upm.app2.gui.fx.components.entries;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class EntryIntegerSpinner extends Spinner<Integer> implements EntryField {
    private final int min;

    public EntryIntegerSpinner(int min, int max) {
        this.min = min;
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, min);
        this.setValueFactory(factory);
        this.setEditable(false);
    }

    @Override
    public String value() {
        return String.valueOf(getValue());
    }

    @Override
    public void clear() {
        getValueFactory().setValue(this.min);
    }
}
