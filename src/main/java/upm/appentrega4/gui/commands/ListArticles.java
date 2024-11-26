package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.Command;
import upm.appentrega4.services.ArticleService;

import java.util.List;

public class ListArticles implements Command {
    private final ArticleService articleService;

    public ListArticles(ArticleService articleService) {
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
    public List<Object> execute(String[] params) {
        return this.articleService.findAll()
                .map(Object.class::cast).toList();
    }
}
