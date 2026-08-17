package app;

import app.cli.commands.CreateUser;
import app.cli.commands.Exit;
import app.cli.commands.Help;
import app.data.repositories.UserRepository;
import app.cli.CommandLineInterface;
import app.cli.ErrorHandler;
import app.cli.view.View;
import app.data.repositories.map.UserRepositoryMap;
import app.servicies.UserService;

public class CliDependencyInjector {
    private static final CliDependencyInjector instance = new CliDependencyInjector();

    private final ErrorHandler errorHandler;
    private final View view;
    private final CommandLineInterface commandLineInterface;
    private final UserService userService;
    private final UserRepository userRepository;

    private CliDependencyInjector() {
        this.userRepository = new UserRepositoryMap();
        this.userService = new UserService(this.userRepository);
        this.view = new View();
        this.commandLineInterface = new CommandLineInterface(this.view);
        this.commandLineInterface.add(new Help(this.commandLineInterface));
        this.commandLineInterface.add(new Exit());
        this.commandLineInterface.add(new CreateUser(this.view, this.userService));
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

}
