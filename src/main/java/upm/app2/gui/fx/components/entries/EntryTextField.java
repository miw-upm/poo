package upm.app2.gui.fx.components.entries;

import javafx.scene.control.TextField;

public class EntryTextField extends TextField implements EntryField {

    public EntryTextField(String title) {
        this.setPromptText("Enter " + title);
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
