package upm;

import upm.app.console.version0.CommandLineInterface;
import upm.app.data.repositories.repositories_map.UserRepositoryMap;
import upm.app.services.UserService;

public class App {

    public static void main(String[] args) {
        System.out.println("Run App..");
        new CommandLineInterface(new UserService(new UserRepositoryMap())).runCommand();
    }
}
