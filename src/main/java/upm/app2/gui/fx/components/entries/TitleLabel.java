package upm.app2.gui.fx.components.entries;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class TitleLabel extends Label {

    public TitleLabel(String title) {
        super(formatTitle(title) + ":");
        this.setFont(Font.font("Arial", FontWeight.BOLD, 11));
    }

    private static String formatTitle(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        String spaced = text.replaceAll("(?<!^)(?=[A-Z])", " ");
        return Character.toUpperCase(spaced.charAt(0)) + spaced.substring(1);
    }
}
