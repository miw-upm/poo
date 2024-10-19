package upm.app2023.console.version2;

import upm.app2023.console.View;
import upm.app2023.data.repositories.*;
import upm.app2023.data.repositories.repositories_sql.*;
import upm.app2023.services.ArticleService;
import upm.app2023.services.TagService;
import upm.app2023.services.UserService;

import java.sql.Connection;

public class DependencyInjector {
    private static DependencyInjector dependencyInjector;
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

        Connection connection = new RepositoryMysql().createConnection(); // new RepositoryH2().createConnection();

        this.userRepository = new UserRepositorySql(connection);
        this.articleRepository = new ArticleRepositorySql(connection);
        this.tagRepository = new TagRepositorySql(connection, (ArticleRepositorySql) this.articleRepository);

        this.shoppingCartRepository = new ShoppingCartRepositorySql(connection, (UserRepositorySql) this.userRepository,
                (ArticleRepositorySql) this.articleRepository);

        this.userService = new UserService(this.userRepository);
        this.tagService = new TagService(this.tagRepository, this.articleRepository);
        this.articleService = new ArticleService(this.articleRepository, this.tagRepository);

        this.commandLineInterface = new CommandLineInterface(this.view, this.userService, this.tagService, this.articleService);

        this.errorHandler = new ErrorHandler(this.commandLineInterface, this.view);

        this.shopSeeder = new ShopSeeder(this.articleRepository, this.shoppingCartRepository, this.tagRepository, this.userRepository);

        this.shopSeeder.seed(); //TODO only develop
    }

    public static DependencyInjector getDependencyInjector() {
        if (dependencyInjector == null) {
            dependencyInjector = new DependencyInjector();
        }
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
