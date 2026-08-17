package es.upm.poo.cli;

import es.upm.poo.cli.exceptions.BadRequestException;
import es.upm.poo.cli.exceptions.CommandException;
import es.upm.poo.cli.view.View;

import java.util.*;

public class CommandLineInterface {
    public static final String EXIT = "exit";
    private static final String COMMAND_DELIMITER_EXPRESSION = "[" + Command.COMMAND_SEPARATOR + "\\r\\n]";
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
        Command foundCommand = this.commands.get(command);
        List<String> obligatoryParams = foundCommand.obligatoryParams();
        List<String> optionalParams = foundCommand.optionalParams();
        if (obligatoryParams.isEmpty() && optionalParams.isEmpty()) {
            return new String[0];
        }
        String[] foundParams = scanner.next().split(Command.PARAM_SEPARATOR);
        int maxParams = obligatoryParams.size() + optionalParams.size();
        if (foundParams.length < obligatoryParams.size() || foundParams.length > maxParams) {
            throw new BadRequestException("Parámetros obligatorios: " + obligatoryParams +
                    ", parámetros opcionales: " + optionalParams +
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
