package upm.appentrega4.gui.fx.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class StringListDialog extends Alert {
    public StringListDialog(String header, List<String> content) {
        super(Alert.AlertType.INFORMATION);
        this.setTitle("Information");
        this.setHeaderText(header);
        if (content.isEmpty()) {
            this.getDialogPane().setContent(new Label("Todo correcto"));
        } else if (content.size() == 1) {
            this.getDialogPane().setContent(new Label(content.get(0)));
        } else {
            ListView<String> listView = new ListView<>();
            listView.getItems().addAll(content);
            listView.setPrefWidth(400);
            this.getDialogPane().setContent(listView);
        }
        this.showAndWait();
    }
}
