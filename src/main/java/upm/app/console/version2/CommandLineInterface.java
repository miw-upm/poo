package upm.app.console.version2;

import upm.app.console.View;
import upm.app.data.models.Article;
import upm.app.data.models.Tag;
import upm.app.data.models.User;
import upm.app.services.ArticleService;
import upm.app.services.TagService;
import upm.app.services.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {
    public static final String DELIMITER_ANY_WHITE_SPACE = "\\s";
    private final View view;
    private final UserService userService;
    private final TagService tagService;
    private final ArticleService articleService;

    public CommandLineInterface(View view, UserService userService, TagService tagService, ArticleService articleService) {
        this.view = view;
        this.userService = userService;
        this.tagService = tagService;
        this.articleService = articleService;
    }

    private void help() {
        for (CommandNames command : CommandNames.values()) {
            this.view.show(command.getHelp());
        }
    }

    public boolean runCommands() {
        Scanner scanner = new Scanner(System.in).useDelimiter("[:,\\r\\n]");
        boolean exit;
        do {
            exit = runCommand(scanner);
        } while (!exit);
        return true;
    }

    private boolean runCommand(Scanner scanner) {
        this.view.showCommand();
        CommandNames command = CommandNames.fromValue(scanner.next());
        boolean exit = false;
        switch (command) {
            case CREATE_USER:
                this.createUser(scanner.next().split(";"));
                break;
            case CREATE_ARTICLE:
                this.createArticle(scanner.next().split(";"));
                break;
            case FIND_ARTICLE_BY_TAG_NAME:
                this.findArticleByTagName(scanner.next().split(";"));
                break;
            case FIND_TAG_BY_ARTICLE_BARCODE:
                this.findTagByArticleBarcode(scanner.next().split(";"));
                break;
            case HELP:
                this.help();
                break;
            case EXIT:
                exit = true;
                break;
        }
        return exit;
    }

    private void createUser(String[] values) {
        if (values.length != 3) {
            throw new IllegalArgumentException(CommandNames.CREATE_USER.getHelp());
        }
        User createdUser = this.userService.create(new User(Integer.valueOf(values[0]), values[1], values[2]));
        this.view.show(createdUser.toString());
    }

    private void findArticleByTagName(String[] values) {
        if (values.length != 1) {
            throw new IllegalArgumentException(CommandNames.FIND_ARTICLE_BY_TAG_NAME.getHelp());
        }
        List<Article> articles = this.articleService.findByTagName(values[0]);
        this.view.show(articles.toString());
    }

    private void findTagByArticleBarcode(String[] values) {
        if (values.length != 1) {
            throw new IllegalArgumentException(CommandNames.FIND_ARTICLE_BY_TAG_NAME.getHelp());
        }
        List<Tag> tags = this.tagService.findByArticleBarcodeFunctional(values[0]);
        this.view.show(tags.toString());
    }

    private void createArticle(String[] values) {
        if (values.length != 3 && values.length != 4) {
            throw new IllegalArgumentException(CommandNames.CREATE_ARTICLE.getHelp());
        }
        String provider = null;
        if (values.length == 4) {
            provider = values[3];
        }

        Article createdArticle = this.articleService.create(new Article(values[0], values[1], new BigDecimal(values[2]), provider));
        this.view.show(createdArticle.toString());
    }
}
