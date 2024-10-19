package upm.app2023.console.commands;

import upm.app2023.console.Command;
import upm.app2023.console.View;
import upm.app2023.console.version2.CommandNames;
import upm.app2023.data.models.Article;
import upm.app2023.services.ArticleService;

import java.util.List;

public class FindArticleByTagName implements Command {

    private static final String NAME = "find-article-by-tag-name";
    private static final String HELP = ":<tag> Presenta los artículos asociados a una etiqueta";
    private final ArticleService articleService;
    private final View view;

    public FindArticleByTagName(ArticleService articleService, View view) {
        this.articleService = articleService;
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        if (values.length != 1) {
            throw new BadCommandException(CommandNames.FIND_ARTICLE_BY_TAG_NAME.getHelp());
        }
        List<Article> articles = this.articleService.findByTagName(values[0]);
        this.view.show(articles.toString());
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
