package upm.app2.gui.fx.components.entries;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class TitleLabel extends Label {

    public TitleLabel(String title) {
        super(formatTitle(title) + ":");
        this.getStyleClass().add("title-label");
        setMinWidth(Region.USE_PREF_SIZE);
    }

    private static String formatTitle(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        String spaced = text.replaceAll("(?<!^)(?=[A-Z])", " ");
        return Character.toUpperCase(spaced.charAt(0)) + spaced.substring(1);
    }
}
