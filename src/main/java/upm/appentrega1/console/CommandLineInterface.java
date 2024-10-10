package upm.appentrega1.console;

import upm.appentrega1.data.models.User;
import upm.appentrega1.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {
    private static final String DELIMITER = ":";
    private static final String COMMAND_DELIMITER_EXPRESSION = "[" + DELIMITER + "\\r\\n]";
    private static final String VALUE_DELIMITER = ",";
    private static final String CREATE_USER = "create-user";
    private static final String CREATE_USER_VALUES = "<mobile>,<name>,<address>";
    private static final String LIST_USERS = "list-users";
    private static final String HELP = "help";
    private static final String EXIT = "exit";

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
        String[] values;
        boolean exit = false;
        switch (command) {
            case CREATE_USER:
                values = scanner.next().split(VALUE_DELIMITER);
                if (values.length != CREATE_USER_VALUES.split(VALUE_DELIMITER).length) {
                    throw new IllegalArgumentException("Error en el nº de parametros, valores encontrados " + Arrays.toString(values));
                }
                User createdUser = this.userService.create(new User(Integer.valueOf(values[0]), values[1], values[2]));
                this.view.show(createdUser.toString());
                break;
            case LIST_USERS:
                List<User> users = this.userService.findAll();
                this.view.show(users.toString());
                break;
            case HELP:
                this.view.showBold(HELP);
                this.view.showBold(CREATE_USER + DELIMITER + CREATE_USER_VALUES);
                this.view.showBold(LIST_USERS);
                this.view.showBold(EXIT);
                break;
            case EXIT:
                exit = true;
                break;
            default:
                throw new UnsupportedOperationException("El comando -" + command + "- no se reconoce");
        }
        return exit;
    }
}
