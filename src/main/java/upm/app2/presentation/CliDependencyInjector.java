package upm.app2.presentation;

import upm.app2.data.repositories.ArticleRepository;
import upm.app2.data.repositories.Seeder;
import upm.app2.data.repositories.ShoppingCartRepository;
import upm.app2.data.repositories.UserRepository;
import upm.app2.data.repositories.mysql.ArticleRepositorySql;
import upm.app2.data.repositories.mysql.RepositoryMysql;
import upm.app2.data.repositories.mysql.ShoppingCartRepositorySql;
import upm.app2.data.repositories.mysql.UserRepositorySql;
import upm.app2.presentation.cli.CommandLineInterface;
import upm.app2.presentation.cli.ErrorHandler;
import upm.app2.presentation.cli.commands.*;
import upm.app2.presentation.view.View;
import upm.app2.services.ShoppingCartService;
import upm.app2.services.UserService;

import java.sql.Connection;

public class CliDependencyInjector {
    private static final CliDependencyInjector instance = new CliDependencyInjector();

    private final ErrorHandler errorHandler;
    private final View view;
    private final CommandLineInterface commandLineInterface;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final ArticleRepository articleRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;

    private final Seeder seeder;

    private CliDependencyInjector() {
        Connection connection = new RepositoryMysql().createConnection();
        this.userRepository = new UserRepositorySql(connection);
        this.articleRepository = new ArticleRepositorySql(connection);
        this.shoppingCartRepository = new ShoppingCartRepositorySql(connection, (UserRepositorySql) this.userRepository, (ArticleRepositorySql) this.articleRepository);

        this.seeder = new Seeder(this.userRepository, this.articleRepository, this.shoppingCartRepository);
        this.seeder.seed();

        this.userService = new UserService(this.userRepository, this.shoppingCartRepository);
        this.shoppingCartService = new ShoppingCartService(userRepository, articleRepository, shoppingCartRepository);

        this.view = new View();
        this.commandLineInterface = new CommandLineInterface(this.view);
        this.commandLineInterface.add(new Help(this.commandLineInterface));
        this.commandLineInterface.add(new Exit());
        this.commandLineInterface.add(new CreateUser(this.view, this.userService));
        this.commandLineInterface.add(new ListUsers(this.view, this.userService));
        this.commandLineInterface.add(new FindUserMobilesByCartGreater100(this.view, this.userService));
        this.commandLineInterface.add(new CreateShoppingCart(this.view, this.shoppingCartService));
        this.commandLineInterface.add(new AddShoppingCart(this.view, this.shoppingCartService));

        this.errorHandler = new ErrorHandler();
    }

    public static CliDependencyInjector getInstance() {
        return CliDependencyInjector.instance;
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

    public ShoppingCartService getShoppingCartService() {
        return shoppingCartService;
    }

    public Seeder getSeeder() {
        return seeder;
    }
}
