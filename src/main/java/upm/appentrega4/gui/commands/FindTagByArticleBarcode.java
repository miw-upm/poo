package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.Command;
import upm.appentrega4.services.TagService;

import java.util.List;

public class FindTagByArticleBarcode implements Command {
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
    public List<Object> execute(String[] params) {
        return this.tagService.findByArticleBarcode(params[0])
                .map(Object.class::cast).toList();
    }
}
