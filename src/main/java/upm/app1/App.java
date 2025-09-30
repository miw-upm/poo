package upm.app1;

import upm.app1.data.repositories.Seeder;
import upm.app1.data.repositories.UserRepositoryMap;
import upm.app1.presentation.cli.CommandLineInterface;
import upm.app1.presentation.cli.ErrorHandler;
import upm.app1.presentation.view.View;
import upm.app1.services.UserService;

public class App {

    public static void main(String[] args) {
        UserRepositoryMap userRepositoryMap = new UserRepositoryMap();
        new Seeder(userRepositoryMap).seed();
        UserService userService = new UserService(userRepositoryMap);
        View view = new View();
        CommandLineInterface cli = new CommandLineInterface(view, userService);
        new ErrorHandler().handlesErrors(cli, view);
    }
}
