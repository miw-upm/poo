package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Article;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.Command;
import upm.appentrega4.services.ArticleService;

import java.math.BigDecimal;
import java.util.List;

public class CreateArticle implements Command {
    private final ArticleService articleService;

    public CreateArticle(ArticleService articleService) {
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
    public List<Object> execute(String[] params) {
        Article createdArticle = this.articleService.create(new Article(params[0], params[1], new BigDecimal(params[2]), params[3]));
        return List.of(createdArticle);
    }
}
