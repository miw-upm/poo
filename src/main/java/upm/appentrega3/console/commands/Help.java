package upm.appentrega3.console.commands;

import upm.appentrega3.console.Command;
import upm.appentrega3.console.CommandLineInterface;
import upm.appentrega3.data.models.Rol;

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
