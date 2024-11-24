package upm.appentrega4;

import upm.appentrega4.gui.CommandLineInterface;
import upm.appentrega4.gui.Controller;
import upm.appentrega4.gui.ErrorHandler;
import upm.appentrega4.gui.View;
import upm.appentrega4.gui.commands.*;
import upm.appentrega4.data.repositories.*;
import upm.appentrega4.data.repositories.mysql.*;
import upm.appentrega4.services.ArticleService;
import upm.appentrega4.services.TagService;
import upm.appentrega4.services.UserService;

import java.sql.Connection;

public class DependencyInjector {
    private static final DependencyInjector instance = new DependencyInjector();
    private final Controller controller;
    private final View view;
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
        this.controller = new Controller();
        this.controller.add(new Help(controller));
        this.controller.add(new Login(this.controller, this.userService));
        this.controller.add(new Logout(this.controller));
        this.controller.add(new CreateUser(this.view, this.userService));
        this.controller.add(new ListUsers(this.view, this.userService));
        this.controller.add(new ListUsers(this.view, this.userService));
        this.controller.add(new CreateArticle(this.view, this.articleService));
        this.controller.add(new ListArticles(this.view, this.articleService));
        this.controller.add(new CreateTag(this.view, this.tagService));
        this.controller.add(new AddArticleToTag(this.view, this.tagService));
        this.controller.add(new FindArticleByTagName(this.view, this.articleService));
        this.controller.add(new FindTagByArticleBarcode(this.view, this.tagService));
    }

    public static DependencyInjector getInstance() {
        return instance;
    }


    public Controller getController() {
        return controller;
    }
    public View getView() {
        return this.view;
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
