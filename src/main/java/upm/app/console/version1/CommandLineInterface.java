package upm.app.console.version1;

import upm.app.data.models.Article;
import upm.app.data.models.Tag;
import upm.app.data.models.User;
import upm.app.services.ArticleService;
import upm.app.services.TagService;
import upm.app.services.UserService;

import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {
    private static final String CREATE_USER = "create-user";
    private static final String FIND_ARTICLES_BY_TAG_NAME = "find-articles-by-tag-name";
    private static final String FIND_TAGS_BY_ARTICLE_BARCODE = "find-tags-by-article-barcode";
    private static final String HELP = "help";
    private static final String EXIT = "exit";

    private final UserService userService;
    private final TagService tagService;
    private final ArticleService articleService;

    public CommandLineInterface(UserService userService, TagService tagService, ArticleService articleService) {
        this.userService = userService;
        this.tagService = tagService;
        this.articleService = articleService;
    }

    public boolean runCommands() {
        Scanner scanner = new Scanner(System.in).useDelimiter("[:,\\r\\n]");
        boolean exit = false;
        while (!exit) {
            exit = runCommand(scanner);
        }
        return true;
    }

    public boolean runCommand(Scanner scanner) {
        System.out.print("gps>");
        String command = scanner.next();
        String[] values;
        boolean exit = false;
        switch (command) {
            case CREATE_USER: //create-user:666000666;Ana G.;Calle...
                values = scanner.next().split(";");
                if (values.length != 3) {
                    throw new IllegalArgumentException("El numero de parametros no es suficiente, se necesitan 3");
                }
                User createdUser = this.userService.create(new User(Integer.valueOf(values[0]), values[1], values[2]));
                System.out.println(createdUser);
                break;
            case FIND_ARTICLES_BY_TAG_NAME: //find-articles-by-tag-name:tag1
                values = scanner.next().split(";");
                if (values.length != 1) {
                    throw new IllegalArgumentException("El numero de parametros no es suficiente, se necesitan 1");
                }
                List<Article> articles = this.articleService.findByTagName(values[0]);
                System.out.println(articles);
                break;
            case FIND_TAGS_BY_ARTICLE_BARCODE: //find-tags-by-article-barcode:8412345123450
                values = scanner.next().split(";");
                if (values.length != 1) {
                    throw new IllegalArgumentException("El numero de parametros no es suficiente, se necesitan 1");
                }
                List<Tag> tags = this.tagService.findByArticleBarcode(values[0]);
                System.out.println(tags);
                break;
            case HELP:
                System.out.println(HELP);
                System.out.println(CREATE_USER);
                System.out.println(FIND_ARTICLES_BY_TAG_NAME);
                System.out.println(FIND_TAGS_BY_ARTICLE_BARCODE);
                System.out.println(EXIT);
                break;
            case EXIT:
                exit = true;
                break;
            default:
                throw new UnsupportedOperationException("El comando no se reconoce");
        }
        return exit;
    }
}
