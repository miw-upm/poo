package upm.appentrega4.gui.fx.components;

import javafx.scene.control.TextField;

public class BarcodeField extends TextField {

    public BarcodeField() {
        super();
    }

    @Override
    public void replaceText(int start, int end, String text) {
        // Validate input before replacing text
        if (isValidInput(text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        // Validate input before replacing selection
        if (isValidInput(text)) {
            super.replaceSelection(text);
        }
    }

    private boolean isValidInput(String text) {
        // Allow only numeric characters and ensure length does not exceed 9 digits
        return text.matches("\\d*") && (getText().length() + text.length() <= 9);
    }

    public boolean isValidMobileNumber() {
        // Check if the current text contains exactly 9 digits
        return getText().length() == 13;
    }
}

