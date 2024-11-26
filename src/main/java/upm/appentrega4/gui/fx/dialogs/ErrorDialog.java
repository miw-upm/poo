package upm.appentrega4.gui.fx.dialogs;

import javafx.scene.control.Alert;

public class ErrorDialog extends Alert {
    public ErrorDialog(String header, String content) {
        super(AlertType.ERROR);
        this.setTitle(header);
        this.setHeaderText(content);
        this.showAndWait();
    }
}
