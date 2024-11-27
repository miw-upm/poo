package upm.appentrega4.gui.commands;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import upm.appentrega4.gui.Command;
import upm.appentrega4.gui.fx.GraphicalUserInterfaceFX;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand implements Command {

    protected void preparedForm() {
        GraphicalUserInterfaceFX.getInstance().getContentArea().getChildren().clear();
        List<TextField> fields = new ArrayList<>();
        for (String fieldName : this.params()) {
            Label label = new Label(fieldName + ":");
            label.setPrefWidth(60);
            TextField field = new TextField();
            field.setPromptText("Enter " + fieldName);
            fields.add(field);
            HBox fieldRow = new HBox(5);
            fieldRow.getChildren().addAll(label, field);
            GraphicalUserInterfaceFX.getInstance().getContentArea().getChildren().add(fieldRow);
        }
        Button submit = new Button("submit");
        submit.setOnAction(this.submitActionHandler(fields));
        GraphicalUserInterfaceFX.getInstance().getContentArea().getChildren().add(submit);
    }

    protected EventHandler<ActionEvent> submitActionHandler(List<TextField> fields) {
        return actionEvent -> {
            try {
                this.executeAction(fields);
            } catch (Exception e) {
                GraphicalUserInterfaceFX.getInstance().getStatus().error(this.name() + ": " + e.getMessage());
            }
        };
    }

    public void executeAction(List<TextField> fields) {
        //None action
    }

}
