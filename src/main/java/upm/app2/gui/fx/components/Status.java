package upm.app2.gui.fx.components;

import javafx.scene.control.Label;

public class Status extends Label {
    private static final String STYLE_BAR = "status-bar";
    private static final String STYLE_INFO = "status-info";
    private static final String STYLE_SUCCESS = "status-success";
    private static final String STYLE_ERROR = "status-error";

    public Status() {
        getStyleClass().add(STYLE_BAR);
        setMaxWidth(Double.MAX_VALUE);
        updateStyle(STYLE_INFO);
        info("Status");
    }

    public void successful(String message) {
        updateStyle(STYLE_SUCCESS);
        setText("✔ " + message);
    }

    public void info(String message) {
        updateStyle(STYLE_INFO);
        setText("ⓘ " + message);
    }

    public void error(String message) {
        updateStyle(STYLE_ERROR);
        setText("✘ " + message);
    }

    private void updateStyle(String newStyle) {
        getStyleClass().removeAll(STYLE_INFO, STYLE_SUCCESS, STYLE_ERROR);
        if (!getStyleClass().contains(newStyle)) {
            getStyleClass().add(newStyle);
        }
    }
}
