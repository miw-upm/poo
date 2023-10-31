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
    private final UserService userService;
    private final TagService tagService;
    private final ArticleService articleService;

    public CommandLineInterface(UserService userService, TagService tagService, ArticleService articleService) {
        this.userService = userService;
        this.tagService = tagService;
        this.articleService = articleService;
    }

    public void runCommand() {
        System.out.print("gps>");
        Scanner scanner = new Scanner(System.in).useDelimiter("[:,\\r\\n]");
        String command = scanner.next();
        String[] values = scanner.next().split(";");
        switch (command) {
            case CREATE_USER: //create-user:666000666;Ana G.;Calle...
                User createdUser = this.userService.create(new User(Integer.valueOf(values[0]), values[1], values[2]));
                System.out.println(createdUser);
                break;
            case FIND_ARTICLES_BY_TAG_NAME: //find-articles-by-tag-name:tag1
                List<Article> articles = this.articleService.findByTagName(values[0]);
                System.out.println(articles);
                break;
            case FIND_TAGS_BY_ARTICLE_BARCODE: //find-tags-by-article-barcode:8412345123450
                List<Tag> tags = this.tagService.findByArticleBarcode(values[0]);
                System.out.println(tags);
                break;
        }

    }
}
