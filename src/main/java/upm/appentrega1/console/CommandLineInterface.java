package upm.appentrega1.console;

import upm.appentrega1.data.models.User;
import upm.appentrega1.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {
    private static final String COMMAND_DELIMITER = ":";
    private static final String PARAM_DELIMITER = ",";
    private static final String COMMAND_DELIMITER_EXPRESSION = "[" + COMMAND_DELIMITER + "\\r\\n]";
    private static final String HELP = "help";
    private static final String EXIT = "exit";
    private static final String CREATE_USER = "create-user";
    private static final String CREATE_USER_PARAMS = "<mobile>,<name>,<address>";
    private static final String LIST_USERS = "list-users";

    private final View view;

    private final UserService userService;

    public CommandLineInterface(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    public void runCommands() {
        Scanner scanner = new Scanner(System.in).useDelimiter(COMMAND_DELIMITER_EXPRESSION);
        boolean exit;
        do {
            exit = runCommand(scanner);
        } while (!exit);
    }

    public boolean runCommand(Scanner scanner) {
        this.view.showCommand();
        String command = scanner.next();
        switch (command) {
            case HELP:
                this.help();
                break;
            case EXIT: {
                return true;
            }
            case CREATE_USER:
                this.createUser(scanner);
                break;
            case LIST_USERS:
                this.listUsers();
                break;
            default:
                throw new UnsupportedOperationException("El comando -" + command + "- no se reconoce");
        }
        return false;
    }

    private void createUser(Scanner scanner) {
        String[] params = scanner.next().split(PARAM_DELIMITER);
        if (params.length != CREATE_USER_PARAMS.split(PARAM_DELIMITER).length) {
            throw new IllegalArgumentException("Error en el nº de parametros, valores encontrados " + Arrays.toString(params));
        }
        User createdUser = this.userService.create(new User(Integer.valueOf(params[0]), params[1], params[2]));
        this.view.show(createdUser.toString());
    }

    private void help() {
        this.view.showBold(HELP);
        this.view.showBold(CREATE_USER + COMMAND_DELIMITER + CREATE_USER_PARAMS);
        this.view.showBold(LIST_USERS);
        this.view.showBold(EXIT);
    }

    private void listUsers() {
        List<User> users = this.userService.findAll();
        this.view.show(users.toString());
    }

}
