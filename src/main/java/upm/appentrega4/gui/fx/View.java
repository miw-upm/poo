package upm.appentrega4.gui.fx;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import upm.appentrega4.gui.fx.components.Status;

public class View {
    private static final View view = new View();
    private VBox contentArea;
    private Status status;

    private Label UserLabel;

    public static View instance() {
        return view;
    }

    public VBox getContentArea() {
        return this.contentArea;
    }

    public void setContentArea(VBox contentArea) {
        this.contentArea = contentArea;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Label getUserLabel() {
        return UserLabel;
    }

    public void setUserLabel(Label userLabel) {
        UserLabel = userLabel;
    }
}
