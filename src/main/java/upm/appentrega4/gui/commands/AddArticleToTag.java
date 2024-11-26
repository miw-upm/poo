package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.Tag;
import upm.appentrega4.gui.Command;
import upm.appentrega4.services.TagService;

import java.util.List;

public class AddArticleToTag implements Command {
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
    public List<Object> execute(String[] params) {
        Tag tagUpdated = this.tagService.addArticle(params[0], params[1]);
        return List.of(tagUpdated);
    }
}
