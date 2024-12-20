package upm.appentrega3;

import upm.appentrega3.console.CommandLineInterface;
import upm.appentrega3.console.ErrorHandler;
import upm.appentrega3.console.View;
import upm.appentrega3.console.commands.*;
import upm.appentrega3.data.repositories.*;
import upm.appentrega3.data.repositories.mysql.*;
import upm.appentrega3.services.ArticleService;
import upm.appentrega3.services.TagService;
import upm.appentrega3.services.UserService;

import java.sql.Connection;

public class DependencyInjector {
    private static final DependencyInjector instance = new DependencyInjector();
    private final ErrorHandler errorHandler;
    private final View view;
    private final CommandLineInterface commandLineInterface;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShopSeeder shopSeeder;
    private final UserService userService;
    private final ArticleService articleService;
    private final TagService tagService;

    private DependencyInjector() {
        Connection connection = new RepositoryH2().createConnection();
        this.userRepository = new UserRepositorySql(connection);
        this.articleRepository = new ArticleRepositorySql(connection);
        this.tagRepository = new TagRepositorySql(connection, (ArticleRepositorySql) this.articleRepository);
        this.shoppingCartRepository = new ShoppingCartRepositorySql(connection, (UserRepositorySql) this.userRepository, (ArticleRepositorySql) this.articleRepository);

        this.shopSeeder = new ShopSeeder(this.userRepository, this.articleRepository, this.tagRepository, this.shoppingCartRepository);
        this.shopSeeder.seed(); //TODO only develop

        this.userService = new UserService(this.userRepository);
        this.articleService = new ArticleService(this.articleRepository, this.tagRepository);
        this.tagService = new TagService(this.tagRepository, this.articleRepository);

        this.view = new View();
        this.commandLineInterface = new CommandLineInterface(this.view);
        this.commandLineInterface.add(new Help(this.commandLineInterface));
        this.commandLineInterface.add(new Exit());
        this.commandLineInterface.add(new Login(this.commandLineInterface, this.userService));
        this.commandLineInterface.add(new Logout(this.commandLineInterface));
        this.commandLineInterface.add(new CreateUser(this.view, this.userService));
        this.commandLineInterface.add(new ListUsers(this.view, this.userService));
        this.commandLineInterface.add(new CreateArticle(this.view, this.articleService));
        this.commandLineInterface.add(new ListArticles(this.view, this.articleService));
        this.commandLineInterface.add(new CreateTag(this.view, this.tagService));
        this.commandLineInterface.add(new AddArticleToTag(this.view, this.tagService));
        this.commandLineInterface.add(new FindArticleByTagName(this.view, this.articleService));
        this.commandLineInterface.add(new FindTagByArticleBarcode(this.view, this.tagService));
        this.errorHandler = new ErrorHandler(this.commandLineInterface, this.view);
    }

    public static DependencyInjector getInstance() {
        return instance;
    }

    public void run() {
        this.errorHandler.handlesErrors();
    }

    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    public View getView() {
        return this.view;
    }

    public CommandLineInterface getCommandLineInterface() {
        return this.commandLineInterface;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public ArticleRepository getArticleRepository() {
        return this.articleRepository;
    }

    public ShopSeeder getShopSeeder() {
        return this.shopSeeder;
    }

    public UserService getUserService() {
        return this.userService;
    }

    public ArticleService getArticleService() {
        return this.articleService;
    }

    public TagRepository getTagRepository() {
        return this.tagRepository;
    }

    public ShoppingCartRepository getShoppingCartRepository() {
        return this.shoppingCartRepository;
    }

    public TagService getTagService() {
        return this.tagService;
    }
}
