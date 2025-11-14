package upm.app2.gui.fx.components.entries;

import javafx.scene.control.TextField;

public class EntryTextField extends TextField implements EntryField {
    private static final String FORM_TEXT_FIELD_STYLE = "form-text-field";

    public EntryTextField(String title) {
        this.setPromptText("Enter " + title);
        this.getStyleClass().add(FORM_TEXT_FIELD_STYLE);
    }

    @Override
    public String value() {
        return this.getText();
    }

    @Override
    public void clear() {
        this.setText("");
    }
}
