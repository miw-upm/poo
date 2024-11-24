package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.Command;
import upm.appentrega4.gui.View;
import upm.appentrega4.services.ArticleService;

import java.util.List;

public class FindArticleByTagName implements Command {
    private final View view;
    private final ArticleService articleService;

    public FindArticleByTagName(View view, ArticleService articleService) {
        this.view = view;
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
    public List<String> execute(String[] params) {
        return this.articleService.findByTagName(params[0]).map(article -> article.toString()).toList();
    }
}
