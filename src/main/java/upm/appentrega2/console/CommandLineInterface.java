package upm.appentrega2.console;

import upm.appentrega2.data.models.User;
import upm.appentrega2.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {
    private static final String COMMAND_DELIMITER_EXPRESSION = "[" + Delimiters.COMMAND.getValue() + "\\r\\n]";
    private final View view;
    private final UserService userService;

    public CommandLineInterface(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    public boolean runCommands() {
        Scanner scanner = new Scanner(System.in).useDelimiter(COMMAND_DELIMITER_EXPRESSION);
        boolean exit;
        do {
            exit = runCommand(scanner);
        } while (!exit);
        return true;
    }

    public boolean runCommand(Scanner scanner) {
        this.view.showCommand();
        Commands command = Commands.fromValue(scanner.next());
        boolean exit = false;
        switch (command) {
            case HELP:
                this.help();
                break;
            case EXIT:
                exit = true;
                break;
            case CREATE_USER:
                this.createUser(scanner, command);
                break;
            case LIST_USERS:
                this.listUsers();
                break;
            default:
                throw new UnsupportedOperationException("El comando -" + command + "- no se reconoce");
        }
        return exit;
    }

    private void help() {
        for (Commands aCommand : Commands.values()) {
            this.view.showBold(aCommand.getHelp());
        }
    }

    private void createUser(Scanner scanner, Commands command) {
        String[] values = scanner.next().split(Delimiters.PARAM.getValue());
        if (values.length != command.length()) {
            throw new IllegalArgumentException("Error en el nº de parametros, valores encontrados " + Arrays.toString(values));
        }
        User createdUser = this.userService.create(new User(Integer.valueOf(values[0]), values[1], values[2]));
        this.view.show(createdUser.toString());
    }

    private void listUsers() {
        List<User> users = this.userService.findAll();
        this.view.show(users.toString());
    }

}
