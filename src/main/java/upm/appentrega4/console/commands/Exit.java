package upm.appentrega4.console.commands;

import upm.appentrega4.console.Command;
import upm.appentrega4.console.CommandLineInterface;
import upm.appentrega4.data.models.Rol;

import java.util.List;

public class Exit implements Command {

    @Override
    public String name() {
        return CommandLineInterface.EXIT;
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
        return "Termina la ejecución";
    }

    @Override
    public void execute(String[] values) {
        // Nothing to do, it never gets executed
    }

}
