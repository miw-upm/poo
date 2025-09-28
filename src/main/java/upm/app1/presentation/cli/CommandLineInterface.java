package upm.app1.presentation.cli;

import upm.app1.data.models.User;
import upm.app1.presentation.view.View;
import upm.app1.services.UserService;

import java.util.Scanner;

public class CommandLineInterface {
    private static final String COMMAND_PARAM_SEPARATOR = "#";
    private static final String PARAM_SEPARATOR = ",";
    private static final String INPUT_DELIMITER = "[" + COMMAND_PARAM_SEPARATOR + "\\r\\n]";

    private final View view;
    private final UserService userService;

    public CommandLineInterface(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    private void help() {
        for (CommandNames command : CommandNames.values()) {
            this.view.show(command.getHelp());
        }
    }

    public boolean runCommands() {
        Scanner scanner = new Scanner(System.in).useDelimiter(INPUT_DELIMITER);
        boolean exit = false;
        while (!exit) {
            exit = runCommand(scanner);
        }
        return true;
    }

    private boolean runCommand(Scanner scanner) {
        this.view.showCommandPrompt();
        CommandNames command = CommandNames.fromValue(scanner.next());
        boolean exit = false;
        switch (command) {
            case CREATE_USER:
                this.createUser(scanner);
                break;
            case FIND_ALL_USERS:
                this.findAllUsers();
                break;
            case HELP:
                this.help();
                break;
            case EXIT:
                exit = true;
                break;
        }
        return exit;
    }

    private void findAllUsers() {
        this.view.showList("Usuarios", this.userService.findAll());
    }

    private void createUser(Scanner scanner) {
        String[] values = scanner.next().split(PARAM_SEPARATOR);
        if (values.length != 3) {
            throw new IllegalArgumentException(CommandNames.CREATE_USER.getHelp());
        }
        User createdUser = this.userService.create(new User(Integer.valueOf(values[0]), values[1], values[2]));
        this.view.show(createdUser.toString());
    }

}
