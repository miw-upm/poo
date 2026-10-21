package es.upm.poo.configuration;

import es.upm.poo.presentation.cli.CommandLineInterface;
import es.upm.poo.presentation.ErrorHandler;
import es.upm.poo.presentation.cli.commands.CreateUser;
import es.upm.poo.presentation.cli.commands.Exit;
import es.upm.poo.presentation.cli.commands.FindAllUsers;
import es.upm.poo.presentation.cli.commands.Help;
import es.upm.poo.presentation.view.View;
import es.upm.poo.data.repositories.UserRepository;
import es.upm.poo.data.repositories.map.UserRepositoryMap;
import es.upm.poo.services.UserService;

public class DependencyInjector {
    private static final DependencyInjector instance = new DependencyInjector();

    private final ErrorHandler errorHandler;
    private final View view;
    private final CommandLineInterface commandLineInterface;

    private final UserService userService;
    private final UserRepository userRepository;

    private DependencyInjector() {
        this.userRepository = new UserRepositoryMap();
        this.userService = new UserService(this.userRepository);
        new Seeder(this.userService).seed();
        this.view = new View();
        this.commandLineInterface = new CommandLineInterface(this.view);
        this.commandLineInterface.add(new Help(this.commandLineInterface));
        this.commandLineInterface.add(new Exit());

        this.commandLineInterface.add(new CreateUser(this.view, this.userService));
        this.commandLineInterface.add(new FindAllUsers(this.view, this.userService));

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

}
