package upm.app2;

import upm.app2.data.repositories.ArticleRepository;
import upm.app2.data.repositories.Seeder;
import upm.app2.data.repositories.ShoppingCartRepository;
import upm.app2.data.repositories.UserRepository;
import upm.app2.data.repositories.map.ArticleRepositoryMap;
import upm.app2.data.repositories.map.ShoppingCartRepositoryMap;
import upm.app2.data.repositories.map.UserRepositoryMap;
import upm.app2.presentation.cli.CommandLineInterface;
import upm.app2.presentation.cli.ErrorHandler;
import upm.app2.presentation.cli.commands.CreateUser;
import upm.app2.presentation.cli.commands.Exit;
import upm.app2.presentation.cli.commands.Help;
import upm.app2.presentation.cli.commands.ListUsers;
import upm.app2.presentation.view.View;
import upm.app2.services.UserService;

public class DependencyInjector {
    private static final DependencyInjector instance = new DependencyInjector();
    private final ErrorHandler errorHandler;
    private final View view;
    private final CommandLineInterface commandLineInterface;
    private final UserService userService;
    private final ArticleRepository articleRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;

    private final Seeder seeder;

    private DependencyInjector() {
        this.userRepository = new UserRepositoryMap();
        this.articleRepository = new ArticleRepositoryMap();
        this.shoppingCartRepository = new ShoppingCartRepositoryMap();

        this.seeder = new Seeder(this.userRepository, this.articleRepository, this.shoppingCartRepository);
        this.seeder.seed();

        this.userService = new UserService(this.userRepository);

        this.view = new View();
        this.commandLineInterface = new CommandLineInterface(this.view);
        this.commandLineInterface.add(new Help(this.commandLineInterface));
        this.commandLineInterface.add(new Exit());
        this.commandLineInterface.add(new CreateUser(this.view, this.userService));
        this.commandLineInterface.add(new ListUsers(this.view, this.userService));

        this.errorHandler = new ErrorHandler();
    }

    public static DependencyInjector getInstance() {
        return DependencyInjector.instance;
    }

    public void run() {
        this.errorHandler.handlesErrors(this.commandLineInterface, this.view);
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public View getView() {
        return view;
    }

    public CommandLineInterface getCommandLineInterface() {
        return commandLineInterface;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    public ShoppingCartRepository getShoppingCartRepository() {
        return shoppingCartRepository;
    }

    public Seeder getSeeder() {
        return seeder;
    }
}
