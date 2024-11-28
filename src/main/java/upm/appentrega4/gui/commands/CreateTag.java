package upm.appentrega4.gui.commands;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import upm.appentrega4.data.models.CreationTag;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.Tag;
import upm.appentrega4.gui.fx.GraphicalUserInterfaceFX;
import upm.appentrega4.gui.fx.components.ArticleComboBox;
import upm.appentrega4.gui.fx.components.RequiredTextField;
import upm.appentrega4.gui.fx.dialogs.EntityListDialog;
import upm.appentrega4.services.ArticleService;
import upm.appentrega4.services.TagService;

import java.util.List;

public class CreateTag extends AbstractCommand {
    private final TagService tagService;
    private final ArticleService articleService;

    public CreateTag(TagService tagService, ArticleService articleService) {
        this.tagService = tagService;
        this.articleService = articleService;
    }

    @Override
    public String name() {
        return "create-tag";
    }

    @Override
    public List<String> params() {
        return List.of("name", "description", "barcode");
    }

    @Override
    public List<Rol> allowedRoles() {
        return Rol.authenticated();
    }

    @Override
    public String helpMessage() {
        return "Se crea una etiqueta";
    }

    @Override
    public void execute() {
        ObservableList<Node> contentArea = GraphicalUserInterfaceFX.getInstance().getContentArea().getChildren();
        contentArea.clear();
        RequiredTextField nameField = new RequiredTextField(params().get(0), 3);
        RequiredTextField descriptionField = new RequiredTextField(params().get(1), 3);
        ArticleComboBox articleComboBox = new ArticleComboBox(this.articleService);
        Button submit = new Button("submit");
        contentArea.addAll(nameField, descriptionField, articleComboBox, submit);

        submit.disableProperty().bind(
                Bindings.or(
                        Bindings.or(
                                nameField.observableInvalid(),
                                descriptionField.observableInvalid()
                        ),
                        articleComboBox.observableNotSelect()
                )
        );
        submit.setOnAction(actionEvent -> {
            List<String> values = List.of(
                    nameField.getText(),
                    descriptionField.getText(),
                    articleComboBox.getSelectionModel().getSelectedItem().getKey());
            this.submitActionHandler(values).handle(actionEvent);
        });
    }

    @Override
    public void executeAction(List<String> fields) {
        Tag createdTag = this.tagService.create(
                new CreationTag(fields.get(0), fields.get(1), fields.get(2)));
        GraphicalUserInterfaceFX.getInstance().getStatus().successful("Etiqueta creada correctamente");
        new EntityListDialog(this.name(), List.of(createdTag));

    }
}
