package upm.app.console;

import upm.app.data.models.User;
import upm.app.services.UserService;

import java.util.Scanner;

public class CommandLineInterface {
    private static final String CREATE_USER = "create-user";
    private UserService userService;

    public CommandLineInterface(UserService userService) {
        this.userService = userService;
    }

    public void runCommand(){
        System.out.print("gps>");
        Scanner scanner = new Scanner(System.in).useDelimiter("[:,\\r\\n]");
        String command = scanner.next();
        switch (command) {
            case CREATE_USER:
                String[] values = scanner.next().split(";");
                User user = this.userService.create(new User(Integer.valueOf(values[0]),values[1],values[2]));
                System.out.println(user);
        }

    }
}
