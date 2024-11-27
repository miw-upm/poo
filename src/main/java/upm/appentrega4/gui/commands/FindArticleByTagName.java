package upm.appentrega4.gui.commands;

import javafx.scene.control.TextField;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.fx.View;
import upm.appentrega4.gui.fx.dialogs.EntityListDialog;
import upm.appentrega4.services.ArticleService;

import java.util.List;

public class FindArticleByTagName extends AbstractCommand {
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
    public void execute() {
        this.preparedForm();
    }

    @Override
    public void executeAction(List<TextField> fields) {
        new EntityListDialog(this.name(), this.articleService.findByTagName(fields.get(0).getText())
                .map(Object.class::cast).toList());
        View.instance().getStatus().successful("Consulta realizada");
    }
}
