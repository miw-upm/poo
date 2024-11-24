package upm.appentrega4.console.commands;

import upm.appentrega4.console.Command;
import upm.appentrega4.console.CommandLineInterface;
import upm.appentrega4.data.models.Rol;

import java.util.List;

public class Help implements Command {
    private final CommandLineInterface commandLineInterface;

    public Help(CommandLineInterface commandLineInterface) {
        this.commandLineInterface = commandLineInterface;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public List<String> params() {
        return List.of();
    }

    @Override
    public List<Rol> allowedRoles() {
        return Rol.all();
    }

    @Override
    public String helpMessage() {
        return "Muestra la ayuda";
    }

    @Override
    public void execute(String[] params) {
        this.commandLineInterface.help();
    }
}
