package upm.app;

import upm.app.console.CommandLineInterface;
import upm.app.console.ErrorHandler;
import upm.app.console.View;
import upm.app.data.repositories.*;
import upm.app.data.repositories.repositories_map.ShoppingCartRepositoryMap;
import upm.app.data.repositories.repositories_map.TagRepositoryMap;
import upm.app.data.repositories.repositories_map.UserRepositoryMap;
import upm.app.data.repositories.repositories_mysql.ArticleRepositoryMysql;
import upm.app.data.repositories.repositories_mysql.UserRepositoryMysql;
import upm.app.services.ArticleService;
import upm.app.services.TagService;
import upm.app.services.UserService;

import java.sql.Statement;

public class DependencyInjector {
    private static final DependencyInjector dependencyInjector = new DependencyInjector();
    private final ErrorHandler errorHandler;
    private final View view;
    private final CommandLineInterface commandLineInterface;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final TagService tagService;
    private final ArticleService articleService;

    private final ShoppingCartRepository shoppingCartRepository;

    private final ShopSeeder shopSeeder;

    private DependencyInjector() {
        this.view = new View();

        this.userRepository = new UserRepositoryMysql();
        this.tagRepository = new TagRepositoryMap();  //TODO
        this.articleRepository = new ArticleRepositoryMysql(); //TODO
        this.shoppingCartRepository = new ShoppingCartRepositoryMap();

        this.userService = new UserService(this.userRepository);
        this.tagService = new TagService(this.tagRepository, this.articleRepository);
        this.articleService = new ArticleService(this.articleRepository, this.tagRepository);

        this.commandLineInterface = new CommandLineInterface(this.view, this.userService, this.tagService, this.articleService);

        this.errorHandler = new ErrorHandler(this.commandLineInterface, this.view);

        this.shopSeeder = new ShopSeeder(this.articleRepository, this.shoppingCartRepository, this.tagRepository, this.userRepository);

        this.shopSeeder.seed(); //TODO only develop
    }

    public static DependencyInjector getDependencyInjector() {
        return dependencyInjector;
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

    public TagRepository getTagRepository() {
        return this.tagRepository;
    }

    public ArticleRepository getArticleRepository() {
        return this.articleRepository;
    }

    public ShoppingCartRepository getShoppingCartRepository() {
        return this.shoppingCartRepository;
    }

    public UserService getUserService() {
        return this.userService;
    }

    public TagService getTagService() {
        return this.tagService;
    }

    public ArticleService getArticleService() {
        return this.articleService;
    }

    public ShopSeeder getShopSeeder() {
        return shopSeeder;
    }
}
