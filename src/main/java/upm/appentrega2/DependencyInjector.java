package upm.appentrega2;

import upm.appentrega2.console.CommandLineInterface;
import upm.appentrega2.console.ErrorHandler;
import upm.appentrega2.console.View;
import upm.appentrega2.data.repositories.ArticleRepositoryMap;
import upm.appentrega2.data.repositories.ShopSeeder;
import upm.appentrega2.data.repositories.UserRepositoryMap;
import upm.appentrega2.services.UserService;

public class DependencyInjector {
    private final ErrorHandler errorHandler;
    private final View view;
    private final CommandLineInterface commandLineInterface;
    private final UserRepositoryMap userRepository;
    private final ArticleRepositoryMap articleRepository;
    private final ShopSeeder shopSeeder;
    private final UserService userService;

    public DependencyInjector() {
        this.userRepository = new UserRepositoryMap();
        this.articleRepository = new ArticleRepositoryMap();
        this.shopSeeder= new ShopSeeder(userRepository,articleRepository);
        this.shopSeeder.seed();

        this.userService = new UserService(this.userRepository);

        this.view = new View();
        this.commandLineInterface = new CommandLineInterface(this.view, this.userService);

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

    public UserService getUserService() {
        return this.userService;
    }

}
