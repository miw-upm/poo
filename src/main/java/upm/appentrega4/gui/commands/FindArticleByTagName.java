package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.Command;
import upm.appentrega4.services.ArticleService;

import java.util.List;

public class FindArticleByTagName implements Command {
    private final ArticleService articleService;

    public FindArticleByTagName(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public String name() {
        return "find-article-by-tag-name";
    }

    @Override
    public List<String> params() {
        return List.of("tagName");
    }

    @Override
    public List<Rol> allowedRoles() {
        return Rol.authenticated();
    }

    @Override
    public String helpMessage() {
        return "Presenta los artículos asociados a una etiqueta";
    }

    @Override
    public List<Object> execute(String[] params) {
        return this.articleService.findByTagName(params[0])
                .map(Object.class::cast).toList();
    }
}
