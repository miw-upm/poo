package upm.app2.gui.fx.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Status extends Label {
    private static final String DEFAULT_FONT_FAMILY = "Arial";
    private static final double DEFAULT_FONT_SIZE = 11;
    private static final Insets BUTTONS_PADDING = new Insets(10);

    public Status() {
        super("Status");
        this.initializeStyle();
    }

    private void initializeStyle() {
        this.info("Status");
        this.setFont(Font.font(DEFAULT_FONT_FAMILY, FontWeight.BOLD, DEFAULT_FONT_SIZE));
        this.setPadding(BUTTONS_PADDING);
        this.setTextFill(Color.BLACK);
        this.setMaxWidth(Double.MAX_VALUE);
    }

    public void successful(String message) {
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setText("✓ Successful! " + message);
    }

    public void info(String message) {
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setText("ℹ " + message);
    }

    public void error(String message) {
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setText("⚠ ERROR! " + message);
    }
}
