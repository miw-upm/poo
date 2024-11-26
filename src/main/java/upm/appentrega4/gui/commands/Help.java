package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.Command;
import upm.appentrega4.gui.Controller;

import java.util.List;

public class Help implements Command {
    private final Controller controller;

    public Help(Controller controller) {
        this.controller = controller;
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
    public List<Object> execute(String[] params) {
        return this.controller.help().stream()
                .map(Object.class::cast).toList();
    }
}
