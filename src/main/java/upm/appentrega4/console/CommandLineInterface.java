package upm.appentrega4.console;

import upm.appentrega4.console.exceptions.BadRequestException;
import upm.appentrega4.console.exceptions.ForbiddenException;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.User;

import java.util.*;

public class CommandLineInterface {
    public static final String EXIT = "exit";
    private static final String COMMAND_DELIMITER_EXPRESSION = "[" + Delimiters.COMMAND.getValue() + "\\r\\n]";
    private final Map<String, Command> commands;
    private final View view;
    private User user;

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
        this.view.showCommand(this.userName());
        String command = scanner.next();
        if (!this.commands.containsKey(command)) {
            throw new BadRequestException("Comando '" + command + "' no existe.");
        }
        if (!this.commands.get(command).allowedRoles().contains(this.userRol())) {
            throw new ForbiddenException("rol actual: " + this.userRol() + ", permitidos: " + this.commands.get(command).allowedRoles());
        }
        String[] params = this.scanParamsIfNeededAssured(scanner, command);
        if (EXIT.equals(command)) {
            return true;
        } else {
            this.commands.get(command).execute(params);
        }
        return false;
    }

    private String userName() {
        if (Objects.isNull(this.user)) {
            return "";
        } else {
            return this.user.getName();
        }
    }

    private String[] scanParamsIfNeededAssured(Scanner scanner, String command) {
        List<String> expectedParams = commands.get(command).params();
        if (expectedParams.isEmpty()) {
            return new String[0];
        }
        String[] foundParams = scanner.next().split(Delimiters.PARAM.getValue());
        if (expectedParams.size() != foundParams.length) {
            throw new BadRequestException("Parámetros esperados: " + expectedParams +
                    ", encontrados " + Arrays.toString(foundParams));
        }
        return foundParams;

    }

    public void help() {
        for (Command command : this.commands.values()) {
            if (command.allowedRoles().contains(this.userRol())) {
                this.view.showBold(command.help());
            }
        }
    }

    private Rol userRol() {
        if (Objects.isNull(this.user)) {
            return Rol.NONE;
        } else {
            return this.user.getRol();
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
