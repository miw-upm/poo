package upm.appentrega4.gui.commands;

import javafx.scene.control.TextField;
import upm.appentrega4.data.models.CreationTag;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.Tag;
import upm.appentrega4.gui.fx.View;
import upm.appentrega4.gui.fx.dialogs.EntityListDialog;
import upm.appentrega4.services.TagService;

import java.util.List;

public class CreateTag extends AbstractCommand {
    private final TagService tagService;

    public CreateTag(TagService tagService) {
        this.tagService = tagService;
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
        this.preparedForm();
    }

    @Override
    public void executeAction(List<TextField> fields) {
        Tag createdTag = this.tagService.create(
                new CreationTag(fields.get(0).getText(), fields.get(1).getText(), fields.get(2).getText()));
        View.instance().getStatus().successful("Etiqueta creado correctamente");
        new EntityListDialog(this.name(), List.of(createdTag));

    }
}
