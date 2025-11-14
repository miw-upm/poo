package upm.app2.gui.fx.components;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Status extends Label {
    private static final String BAR_STYLE = "status-bar";
    private static final String INFO_STYLE = "status-info";
    private static final String SUCCESS_STYLE = "status-success";
    private static final String ERROR_STYLE = "status-error";

    private static final int MESSAGE_DURATION_SECONDS = 10;

    public Status() {
        getStyleClass().add(BAR_STYLE);
        setMaxWidth(Double.MAX_VALUE);
        updateStyle(INFO_STYLE);
        info();
    }

    public void successful(String message) {
        updateStyle(SUCCESS_STYLE);
        setText("✔ " + message);
        showTemporaryMessage(this::info);
    }

    public void info(String message) {
        updateStyle(INFO_STYLE);
        setText("ⓘ " + message);
    }

    public void info() {
        this.info("");
    }

    public void error(String message) {
        updateStyle(ERROR_STYLE);
        setText("✘ " + message);
        showTemporaryMessage(this::info);
    }

    private void updateStyle(String newStyle) {
        getStyleClass().removeAll(INFO_STYLE, SUCCESS_STYLE, ERROR_STYLE);
        if (!getStyleClass().contains(newStyle)) {
            getStyleClass().add(newStyle);
        }
    }

    private void showTemporaryMessage(Runnable actionAfter) {
        PauseTransition pause = new PauseTransition(Duration.seconds(MESSAGE_DURATION_SECONDS));
        pause.setOnFinished(e -> actionAfter.run());
        pause.play();
    }
}
