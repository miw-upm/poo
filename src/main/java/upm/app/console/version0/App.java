package upm.app.console.version0;


import upm.app.data.repositories.UserRepository;
import upm.app.data.repositories.repositories_map.UserRepositoryMap;
import upm.app.services.UserService;

public class App {

    public static void main(String[] args) {
        System.out.println("Run App..version0");
        UserRepository userRepository = new UserRepositoryMap();
        UserService userService = new UserService(userRepository);
        new CommandLineInterface(userService).runCommand();
    }
}
