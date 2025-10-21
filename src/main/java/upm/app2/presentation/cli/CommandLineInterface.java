package upm.app2.presentation.cli;

import upm.app2.presentation.cli.exceptions.BadRequestException;
import upm.app2.presentation.cli.exceptions.CommandException;
import upm.app2.presentation.view.View;

import java.util.*;

public class CommandLineInterface {
    public static final String EXIT = "exit";
    private static final String COMMAND_DELIMITER_EXPRESSION = "[" + Delimiters.COMMAND_PARAM_SEPARATOR.getValue() + "\\r\\n]";
    private final Map<String, Command> commands;
    private final View view;

    public CommandLineInterface(View view) {
        this.view = view;
        this.commands = new HashMap<>();
    }

    public void add(Command command) {
        this.commands.put(command.name(), command);
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
        this.view.showCommandPrompt();
        String command = scanner.next();
        if (!this.commands.containsKey(command)) {
            throw new CommandException("Comando '" + command + "' no existe.");
        }
        String[] params = this.scanParamsIfNeededAssured(scanner, command);
        if (EXIT.equals(command)) {
            return true;
        } else {
            this.commands.get(command).execute(params);
        }
        return false;
    }

    private String[] scanParamsIfNeededAssured(Scanner scanner, String command) {
        List<String> expectedParams = commands.get(command).params();
        if (expectedParams.isEmpty()) {
            return new String[0];
        }
        String[] foundParams = scanner.next().split(Delimiters.PARAM_SEPARATOR.getValue());
        if (expectedParams.size() != foundParams.length) {
            throw new BadRequestException("Parámetros esperados: " + expectedParams +
                    ", encontrados " + Arrays.toString(foundParams));
        }
        return foundParams;

    }

    public void help() {
        for (Command command : this.commands.values()) {
            this.view.showImportant(command.help());
        }
    }

}
