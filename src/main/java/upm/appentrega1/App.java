package upm.appentrega1;

import upm.appentrega1.console.CommandLineInterface;
import upm.appentrega1.console.View;
import upm.appentrega1.data.repositories.UserRepositoryMap;
import upm.appentrega1.services.UserService;

public class App {

    public static void main(String[] args) {
        UserRepositoryMap userRepository = new UserRepositoryMap();
        UserService userService = new UserService(userRepository);
        View view = new View();
        view.showBold("App. " + App.class.getPackageName() + "  " + upm.appentrega3.console.View.COPY_RIGHT + "UPM.ETSISI.POO");
        new CommandLineInterface(view, userService).runCommands();
    }
}
