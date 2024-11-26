package upm.appentrega4.gui.fx.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.apache.logging.log4j.util.SystemPropertiesPropertySource;
import upm.appentrega4.gui.fx.components.GenericTableView;

import java.util.List;

public class ObjectListDialog extends Alert {
    public ObjectListDialog(String header, List<Object> content) {
        super(AlertType.INFORMATION);
        this.setTitle("Information");
        this.setHeaderText(header);
        if (content.isEmpty()) {
            this.getDialogPane().setContent(new Label("Lista vacia"));
        } else {
            GenericTableView tableView = new GenericTableView(content);
            this.getDialogPane().setContent(tableView);
        }
        this.showAndWait();
    }
}
