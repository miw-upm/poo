package upm.appentrega4.gui.commands;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import upm.appentrega4.gui.Command;
import upm.appentrega4.gui.fx.View;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand implements Command {

    protected void preparedForm() {
        View.instance().getContentArea().getChildren().clear();
        List<TextField> fields = new ArrayList<>();
        for (String fieldName : this.params()) {
            TextField field = new TextField();
            field.setPromptText("Enter " + fieldName);
            fields.add(field);
            View.instance().getContentArea().getChildren().add(field);
        }
        Button submit = new Button("submit");
        submit.setOnAction(this.submitActionHandler(fields));
        View.instance().getContentArea().getChildren().add(submit);
    }

    protected EventHandler<ActionEvent> submitActionHandler(List<TextField> fields) {
        return actionEvent -> {
            try {
                this.executeAction(fields);
            } catch (Exception e) {
                View.instance().getStatus().error("login : " + e.getMessage());
            }
        };
    }

    public void executeAction(List<TextField> fields) {
        //None action
    }

}
