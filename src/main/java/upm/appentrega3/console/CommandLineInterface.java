package upm.appentrega3.console;

import upm.appentrega3.console.exceptions.BadRequestException;
import upm.appentrega3.data.models.Article;
import upm.appentrega3.data.models.Rol;
import upm.appentrega3.data.models.Tag;
import upm.appentrega3.data.models.User;
import upm.appentrega3.services.ArticleService;
import upm.appentrega3.services.TagService;
import upm.appentrega3.services.UserService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CommandLineInterface {
    private static final String COMMAND_DELIMITER_EXPRESSION = "[" + Delimiters.COMMAND.getValue() + "\\r\\n]";
    private final View view;
    private final UserService userService;
    private final ArticleService articleService;
    private final TagService tagService;
    private User user;

    public CommandLineInterface(View view, UserService userService, ArticleService articleService, TagService tagService) {
        this.view = view;
        this.userService = userService;
        this.articleService = articleService;
        this.tagService = tagService;
    }

    public boolean runCommands() {
        Scanner scanner = new Scanner(System.in).useDelimiter(COMMAND_DELIMITER_EXPRESSION);
        boolean exit;
        do {
            exit = runCommand(scanner);
        } while (!exit);
        return true;
    }

    public boolean runCommand(Scanner scanner) {
        this.view.showCommand(this.userName());
        Command command = Command.fromValue(scanner.next(), this.userRol());
        String[] params = this.scanParamsIfNeededAssured(scanner, command);
        switch (command) {
            case HELP -> this.help();
            case EXIT -> {
                return true;
            }
            case LOGIN -> this.login(params);
            case LOGOUT -> this.logout();
            case CREATE_USER -> this.createUser(params);
            case LIST_USERS -> this.listUsers();
            case CREATE_ARTICLE -> this.createArticle(params);
            case LIST_ARTICLES -> this.listArticles();
            case CREATE_TAG -> this.createTag(params);
            case ADD_ARTICLE_TO_TAG -> this.addArticleToTag(params);
            case FIND_ARTICLE_BY_TAG_NAME -> this.findArticleByTagName(params);
            case FIND_TAG_BY_ARTICLE_BARCODE -> this.findTagByArticleBarcode(params);
            default -> throw new UnsupportedOperationException("El comando -" + command + "- no se reconoce");
        }
        return false;
    }

    private void findTagByArticleBarcode(String[] params) {
        List<Tag> tags = this.tagService.findByArticleBarcode(params[0]);
        this.view.show(tags.toString());
    }

    private void findArticleByTagName(String[] params) {
        List<Article> articles = this.articleService.findByTagName(params[0]);
        this.view.show(articles.toString());
    }

    private void addArticleToTag(String[] params) {
        Tag tagUpdated = this.tagService.addArticle(params[0], params[1]);
        this.view.show(tagUpdated.toString());
    }

    private void createTag(String[] params) {
        Tag tagCreated = this.tagService.create(new Tag(params[0], params[1]));
        this.view.show(tagCreated.toString());
    }

    private String userName() {
        if (Objects.isNull(this.user)) {
            return "";
        } else {
            return this.user.getName();
        }
    }

    private Rol userRol() {
        if (Objects.isNull(this.user)) {
            return Rol.NONE;
        } else {
            return this.user.getRol();
        }
    }

    private void logout() {
        this.user = null;
    }

    private void login(String[] params) {
        this.user = this.userService.login(Integer.valueOf(params[0]), params[1]);
    }

    private String[] scanParamsIfNeededAssured(Scanner scanner, Command command) {
        if (command.getParams().length > 0) {
            String[] params = scanner.next().split(Delimiters.PARAM.getValue());
            if (command.getParams().length != params.length) {
                throw new BadRequestException("Parámetros esperados: " + Arrays.toString(command.getParams()) +
                        ", encontrados " + Arrays.toString(params));
            }
            return params;
        }
        return new String[0];
    }

    private void help() {
        for (Command aCommand : Command.values()) {
            String message = aCommand.getHelp(this.userRol());
            if (!message.isEmpty()) {
                this.view.showBold(aCommand.getHelp(this.userRol()));
            }
        }
    }

    private void createUser(String[] params) {
        User createdUser = this.userService.create(new User(Integer.valueOf(params[0]), params[1], params[2], params[3]));
        this.view.show(createdUser.toString());
    }

    private void listUsers() {
        List<User> users = this.userService.findAll();
        this.view.show(users.toString());
    }

    private void createArticle(String[] params) {
        Article createdArticle = this.articleService.create(new Article(params[0], params[1], new BigDecimal(params[2]), params[3]));
        this.view.show(createdArticle.toString());
    }

    private void listArticles() {
        List<Article> articles = this.articleService.findAll();
        this.view.show(articles.toString());
    }

}
