package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.Tag;
import upm.appentrega4.gui.fx.GraphicalUserInterfaceFX;
import upm.appentrega4.gui.fx.dialogs.EntityListDialog;
import upm.appentrega4.services.TagService;

import java.util.List;

public class AddArticleToTag extends AbstractCommand {
    private final TagService tagService;

    public AddArticleToTag(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    public String name() {
        return "add-article-to-tag";
    }

    @Override
    public List<String> params() {
        return List.of("tagName", "articleBarcode");
    }

    @Override
    public List<Rol> allowedRoles() {
        return Rol.authenticated();
    }

    @Override
    public String helpMessage() {
        return "Añadir un artículo a una etiqueta";
    }

    @Override
    public void execute() {
        this.preparedForm();
    }

    @Override
    public void executeAction(List<String> fields) {
        Tag tagUpdated = this.tagService.addArticle(fields.get(0), fields.get(1));
        GraphicalUserInterfaceFX.getInstance().getStatus().successful("Articulo añadido correctamente");
        new EntityListDialog(this.name(), List.of(tagUpdated));
    }
}
