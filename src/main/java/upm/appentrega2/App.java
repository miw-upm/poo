package upm.appentrega2;

import upm.appentrega2.console.CommandLineInterface;
import upm.appentrega2.console.ErrorHandler;
import upm.appentrega2.console.View;
import upm.appentrega2.data.repositories.UserRepositoryMap;
import upm.appentrega2.services.UserService;

public class App {

    public static void main(String[] args) {
        new DependencyInjector().run();
        UserRepositoryMap userRepository = new UserRepositoryMap();
        UserService userService = new UserService(userRepository);
        View view = new View();
        view.showBold("Run App..version2");
        CommandLineInterface commandLineInterface = new CommandLineInterface(view, userService);
        ErrorHandler errorHandler = new ErrorHandler(commandLineInterface, view);
        errorHandler.handlesErrors();
    }
}
