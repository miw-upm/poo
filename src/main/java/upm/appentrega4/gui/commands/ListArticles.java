package upm.appentrega4.gui.commands;

import upm.appentrega4.gui.Command;
import upm.appentrega4.gui.View;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.services.ArticleService;

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
    public List<String> execute(String[] params) {
        return this.articleService.findAll().map(article ->article.toString()).toList();
    }
}
