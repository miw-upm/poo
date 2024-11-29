package upm.appentrega4.gui.fx.components;

import javafx.scene.control.TextField;
import upm.appentrega4.data.models.Article;

public class BarcodeField extends TextField {

    public BarcodeField() {
        super();
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (isValidInput(text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (isValidInput(text)) {
            super.replaceSelection(text);
        }
    }

    private boolean isValidInput(String text) {
        return text.matches("\\d*") && (getText().length() + text.length() <= Article.EUROPEAN_ARTICLE_NUMBER);
    }

    public boolean isValidMobileNumber() {
        return getText().length() == Article.EUROPEAN_ARTICLE_NUMBER;
    }
}

