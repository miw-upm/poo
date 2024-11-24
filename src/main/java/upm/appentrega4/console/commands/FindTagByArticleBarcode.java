package upm.appentrega4.console.commands;

import upm.appentrega4.console.Command;
import upm.appentrega4.console.View;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.services.TagService;

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
        this.tagService.findByArticleBarcode(params[0]).forEach(tag -> this.view.show(tag.toString()));
    }
}
