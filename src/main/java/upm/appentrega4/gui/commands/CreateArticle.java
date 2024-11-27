package upm.appentrega4.gui.commands;

import javafx.scene.control.TextField;
import upm.appentrega4.data.models.Article;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.fx.GraphicalUserInterfaceFX;
import upm.appentrega4.gui.fx.dialogs.EntityListDialog;
import upm.appentrega4.services.ArticleService;

import java.math.BigDecimal;
import java.util.List;

public class CreateArticle extends AbstractCommand {
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
    public void execute() {
        this.preparedForm();
    }

    @Override
    public void executeAction(List<TextField> fields) {
        Article createdArticle = this.articleService.create(
                new Article(fields.get(0).getText(), fields.get(1).getText(), new BigDecimal(fields.get(2).getText()), fields.get(3).getText()));
        GraphicalUserInterfaceFX.getInstance().getStatus().successful("Usuario creado correctamente");
        new EntityListDialog(this.name(), List.of(createdArticle));
    }
}
