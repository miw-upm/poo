package upm.appentrega4.gui.fx.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import upm.appentrega4.gui.fx.components.EntityTableView;

import java.util.List;

public class EntityListDialog extends Alert {
    public EntityListDialog(String header, List<Object> content) {
        super(AlertType.INFORMATION);
        this.setTitle("Information");
        this.setHeaderText(header);
        if (content.isEmpty()) {
            this.getDialogPane().setContent(new Label("No existen resultados!!!"));
        } else {
            EntityTableView tableView = new EntityTableView(content);
            this.getDialogPane().setContent(tableView);
        }
        this.showAndWait();
    }
}
