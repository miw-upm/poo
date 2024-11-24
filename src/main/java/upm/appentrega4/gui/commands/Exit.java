package upm.appentrega4.gui.commands;

import upm.appentrega4.gui.Command;
import upm.appentrega4.gui.CommandLineInterface;
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
    public List<String> execute(String[] values) {
        return null; // Nothing to do, it never gets executed
    }

}
