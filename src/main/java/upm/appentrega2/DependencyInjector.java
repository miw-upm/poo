package upm.appentrega2;

import upm.appentrega2.console.CommandLineInterface;
import upm.appentrega2.console.ErrorHandler;
import upm.appentrega2.console.View;
import upm.appentrega2.data.repositories.ArticleRepository;
import upm.appentrega2.data.repositories.ShopSeeder;
import upm.appentrega2.data.repositories.UserRepository;
import upm.appentrega2.data.repositories.map.ArticleRepositoryMap;
import upm.appentrega2.data.repositories.map.UserRepositoryMap;
import upm.appentrega2.services.ArticleService;
import upm.appentrega2.services.UserService;

public class DependencyInjector {
    private final ErrorHandler errorHandler;
    private final View view;
    private final CommandLineInterface commandLineInterface;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ShopSeeder shopSeeder;
    private final UserService userService;
    private final ArticleService articleService;

    public DependencyInjector() {
        this.userRepository = new UserRepositoryMap();
        this.articleRepository = new ArticleRepositoryMap();
        this.shopSeeder = new ShopSeeder(userRepository, articleRepository);
        this.shopSeeder.seed(); //TODO only develop

        this.userService = new UserService(this.userRepository);
        this.articleService = new ArticleService(this.articleRepository);

        this.view = new View();
        this.commandLineInterface = new CommandLineInterface(this.view, this.userService, this.articleService);

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
}
