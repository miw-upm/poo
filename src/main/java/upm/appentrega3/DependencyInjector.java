package upm.appentrega3;

import upm.appentrega3.console.CommandLineInterface;
import upm.appentrega3.console.ErrorHandler;
import upm.appentrega3.console.View;
import upm.appentrega3.data.repositories.*;
import upm.appentrega3.data.repositories.map.ArticleRepositoryMap;
import upm.appentrega3.data.repositories.map.ShoppingCartRepositoryMap;
import upm.appentrega3.data.repositories.map.TagRepositoryMap;
import upm.appentrega3.data.repositories.map.UserRepositoryMap;
import upm.appentrega3.services.ArticleService;
import upm.appentrega3.services.TagService;
import upm.appentrega3.services.UserService;

public class DependencyInjector {
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

    public DependencyInjector() {
        this.userRepository = new UserRepositoryMap();
        this.articleRepository = new ArticleRepositoryMap();
        this.tagRepository = new TagRepositoryMap();
        this.shoppingCartRepository = new ShoppingCartRepositoryMap();

        this.shopSeeder = new ShopSeeder(this.userRepository, this.articleRepository, this.tagRepository, this.shoppingCartRepository);
        this.shopSeeder.seed(); //TODO only develop

        this.userService = new UserService(this.userRepository);
        this.articleService = new ArticleService(this.articleRepository, this.tagRepository);
        this.tagService = new TagService(this.tagRepository, this.articleRepository);

        this.view = new View();
        this.commandLineInterface = new CommandLineInterface(this.view, this.userService, this.articleService, this.tagService);

        this.errorHandler = new ErrorHandler(this.commandLineInterface, this.view);
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
