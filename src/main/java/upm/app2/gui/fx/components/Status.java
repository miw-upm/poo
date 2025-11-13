package upm.app2.gui.fx.components;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Status extends Label {
    private static final String STYLE_BAR = "status-bar";
    private static final String STYLE_INFO = "status-info";
    private static final String STYLE_SUCCESS = "status-success";
    private static final String STYLE_ERROR = "status-error";
    private static final int MESSAGE_DURATION_SECONDS = 10;

    public Status() {
        getStyleClass().add(STYLE_BAR);
        setMaxWidth(Double.MAX_VALUE);
        updateStyle(STYLE_INFO);
        info("Status");
    }

    public void successful(String message) {
        updateStyle(STYLE_SUCCESS);
        setText("✔ " + message);
        showTemporaryMessage(() -> info("Status"));
    }

    public void info(String message) {
        updateStyle(STYLE_INFO);
        setText("ⓘ " + message);
    }

    public void error(String message) {
        updateStyle(STYLE_ERROR);
        setText("✘ " + message);
        showTemporaryMessage(() -> info("Status"));
    }

    private void updateStyle(String newStyle) {
        getStyleClass().removeAll(STYLE_INFO, STYLE_SUCCESS, STYLE_ERROR);
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
