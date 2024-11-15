package upm.appentrega3.console.commands;

import upm.appentrega3.console.Command;
import upm.appentrega3.console.View;
import upm.appentrega3.data.models.Rol;
import upm.appentrega3.services.ArticleService;

import java.util.List;

public class ListArticles implements Command {
    private final View view;
    private final ArticleService articleService;

    public ListArticles(View view, ArticleService articleService) {
        this.view = view;
        this.articleService = articleService;
    }

    @Override
    public String name() {
        return "list-articles";
    }

    @Override
    public List<String> params() {
        return List.of();
    }

    @Override
    public List<Rol> allowedRoles() {
        return Rol.authenticated();
    }

    @Override
    public String helpMessage() {
        return "Lista los artículos";
    }

    @Override
    public void execute(String[] params) {
        this.articleService.findAll().forEach(article -> this.view.show(article.toString()));
    }
}
