package upm.appentrega3.console.commands;

import upm.appentrega3.console.Command;
import upm.appentrega3.console.CommandLineInterface;
import upm.appentrega3.data.models.Rol;

import java.util.List;

public class Logout implements Command {
    private final CommandLineInterface commandLineInterface;

    public Logout(CommandLineInterface commandLineInterface) {
        this.commandLineInterface = commandLineInterface;
    }

    @Override
    public String name() {
        return "logout";
    }

    @Override
    public List<String> params() {
        return List.of();
    }

    @Override
    public List<Rol> allowedRoles() {
        return Rol.authenticated();
    }

    @Override
    public String helpMessage() {
        return "Cierra sesión";
    }

    @Override
    public void execute(String[] params) {
        this.commandLineInterface.setUser(null);
    }
}
