package upm.appentrega3.console.commands;

import upm.appentrega3.console.Command;
import upm.appentrega3.console.View;
import upm.appentrega3.data.models.Rol;
import upm.appentrega3.services.ArticleService;

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
    public void execute(String[] params) {
        this.articleService.findByTagName(params[0]).forEach(article -> this.view.show(article.toString()));
    }
}
