package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.fx.GraphicalUserInterfaceFX;
import upm.appentrega4.gui.fx.dialogs.EntityListDialog;
import upm.appentrega4.services.TagService;

import java.util.List;

public class FindTagByArticleBarcode extends AbstractCommand {
    private final TagService tagService;

    public FindTagByArticleBarcode(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    public String name() {
        return "find-tag-by-article-barcode";
    }

    @Override
    public List<String> params() {
        return List.of("articleBarcode");
    }

    @Override
    public List<Rol> allowedRoles() {
        return Rol.authenticated();
    }

    @Override
    public String helpMessage() {
        return "Presenta las atiquetas asociadas al artículo";
    }

    @Override
    public void execute() {
        this.preparedForm();
    }

    @Override
    public void executeAction(List<String> fields) {
        new EntityListDialog(this.name(), this.tagService.findByArticleBarcode(fields.get(0))
                .map(Object.class::cast).toList());
        GraphicalUserInterfaceFX.getInstance().getStatus().successful("Consulta realizada");
    }
}
