package upm.appentrega4.gui.fx.components;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class RequiredTextField extends HBox {

    private final TextField textField;
    private final Label nameLabel;

    private final int minLength;

    public RequiredTextField(String name, int minLength) {
        super(5);
        if (minLength < 0) {
            throw new IllegalArgumentException("Minimum length cannot be negative.");
        }
        this.minLength = minLength;

        this.nameLabel = new Label(name + ":");
        this.nameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
        this.nameLabel.setPrefWidth(100);

        this.textField = new TextField();
        this.textField.setPromptText("Enter " + name);
        this.textField.setTooltip(new Tooltip("Minimum " + this.minLength));

        this.getChildren().addAll(nameLabel, textField);

        textField.textProperty().addListener((obs, oldText, newText) -> {
            if (this.isInvalid()) {
                textField.setStyle("-fx-text-fill: red;");
            } else {
                textField.setStyle("-fx-text-fill: black;");
            }
        });
    }

    private boolean isInvalid() {
        String text = textField.getText();
        return text == null || text.length() < minLength;
    }

    public BooleanBinding observableInvalid() {
        return Bindings.createBooleanBinding(
                this::isInvalid,
                this.textField.textProperty()
        );
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public int getMinLength() {
        return minLength;
    }

    public String getName() {
        return nameLabel.getText();
    }
}
