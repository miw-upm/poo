package es.upm.poo.configuration;

import es.upm.poo.cli.commands.CreateUser;
import es.upm.poo.cli.commands.Exit;
import es.upm.poo.cli.commands.Help;
import es.upm.poo.cli.commands.ReadUser;
import es.upm.poo.data.repositories.UserRepository;
import es.upm.poo.cli.CommandLineInterface;
import es.upm.poo.cli.ErrorHandler;
import es.upm.poo.cli.view.View;
import es.upm.poo.data.repositories.map.UserRepositoryMap;
import es.upm.poo.servicies.UserService;

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
        new Seeder(this.userService).seed();
        this.view = new View();
        this.commandLineInterface = new CommandLineInterface(this.view);
        this.commandLineInterface.add(new Help(this.commandLineInterface));
        this.commandLineInterface.add(new Exit());
        this.commandLineInterface.add(new CreateUser(this.view, this.userService));
        this.commandLineInterface.add(new ReadUser(this.view, this.userService));
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
