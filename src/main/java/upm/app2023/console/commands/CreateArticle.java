package upm.app2023.console.commands;

import upm.app2023.console.Command;
import upm.app2023.console.View;
import upm.app2023.console.version2.CommandNames;
import upm.app2023.data.models.Article;
import upm.app2023.services.ArticleService;

import java.math.BigDecimal;

public class CreateArticle implements Command {

    private static final String NAME = "create-article";
    private static final String HELP = ":<barcode>;<summary>;<price>;<provider>? Se crea un articulo, proveedor es opcional";
    private final ArticleService articleService;
    private final View view;

    public CreateArticle(ArticleService articleService, View view) {
        this.articleService = articleService;
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        if (values.length != 3 && values.length != 4) {
            throw new BadCommandException(CommandNames.CREATE_ARTICLE.getHelp());
        }
        String provider = null;
        if (values.length == 4) {
            provider = values[3];
        }

        Article createdArticle = this.articleService.create(new Article(values[0], values[1], new BigDecimal(values[2]), provider));
        this.view.show(createdArticle.toString());
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String help() {
        return NAME + HELP;
    }
}
