package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.Controller;
import upm.appentrega4.gui.fx.View;

import java.util.List;

public class Logout extends AbstractCommand {
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
    public void execute() {
        this.controller.setUser(null);
        View.instance().getStatus().successful("Hasta pronto!!!");
        View.instance().getUserLabel().setText("Not logged");
    }

}
