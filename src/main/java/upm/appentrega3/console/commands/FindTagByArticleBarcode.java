package upm.appentrega3.console.commands;

import upm.appentrega3.console.Command;
import upm.appentrega3.console.View;
import upm.appentrega3.data.models.Rol;
import upm.appentrega3.services.TagService;

import java.util.List;

public class FindTagByArticleBarcode implements Command {
    private final View view;
    private final TagService tagService;

    public FindTagByArticleBarcode(View view, TagService tagService) {
        this.view = view;
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
    public void execute(String[] params) {
        this.view.show(this.tagService.findByArticleBarcode(params[0]).toString());
    }
}
