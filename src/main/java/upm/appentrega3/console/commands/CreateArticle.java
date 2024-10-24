package upm.appentrega3.console.commands;

import upm.appentrega3.console.Command;
import upm.appentrega3.console.View;
import upm.appentrega3.data.models.Article;
import upm.appentrega3.data.models.Rol;
import upm.appentrega3.services.ArticleService;

import java.math.BigDecimal;
import java.util.List;

public class CreateArticle implements Command {
    private final View view;
    private final ArticleService articleService;

    public CreateArticle(View view, ArticleService articleService) {
        this.view = view;
        this.articleService = articleService;
    }

    @Override
    public String name() {
        return "create-article";
    }

    @Override
    public List<String> params() {
        return List.of("barcode", "summary", "price", "provider");
    }

    @Override
    public List<Rol> allowedRoles() {
        return List.of(Rol.ADMIN, Rol.MANAGEMENT);
    }

    @Override
    public String helpMessage() {
        return "Se crea un artículo";
    }

    @Override
    public void execute(String[] params) {
        Article createdArticle = this.articleService.create(new Article(params[0], params[1], new BigDecimal(params[2]), params[3]));
        this.view.show(createdArticle.toString());
    }
}
