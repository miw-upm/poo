package upm.app.console.version0;

import upm.app.console.View;
import upm.app.data.repositories.UserRepository;
import upm.app.data.repositories.repositories_map.UserRepositoryMap;
import upm.app.services.version0.UserService;

public class App {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryMap();
        UserService userService = new UserService(userRepository);
        View view = new View();
        view.showBold("Run App..version0");
        new CommandLineInterface(view, userService).runCommands();
    }
}
