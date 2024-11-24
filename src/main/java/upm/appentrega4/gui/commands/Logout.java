package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.Command;
import upm.appentrega4.gui.Controller;

import java.util.List;

public class Logout implements Command {
    private final Controller controller;

    public Logout(Controller controller) {
        this.controller = controller;
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
    public List<String> execute(String[] params) {
        this.controller.setUser(null);
        return List.of("Hasta pronto!!!");
    }
}
