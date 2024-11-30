package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.User;
import upm.appentrega4.gui.Controller;
import upm.appentrega4.services.UserService;
import upm.appentrega4.gui.fx.GraphicalUserInterfaceFX;

import java.util.List;

public class Login extends AbstractCommand {
    private final UserService userService;
    private final Controller controller;

    public Login(Controller controller, UserService userService) {
        this.controller = controller;
        this.userService = userService;
    }

    @Override
    public String name() {
        return Controller.LOGIN;
    }

    @Override
    public List<String> params() {
        return List.of("mobile", "password");
    }

    @Override
    public List<Rol> allowedRoles() {
        return List.of(Rol.NONE);
    }

    @Override
    public String helpMessage() {
        return "Inicia sesión";
    }

    @Override
    public void execute() {
        this.preparedForm();
    }

    @Override
    public void executeAction(List<String> fields) {
        User userLogged = this.userService.login(Integer.valueOf(fields.get(0)), fields.get(1));
        this.controller.setUser(userLogged);
        GraphicalUserInterfaceFX.getInstance().getStatus().successful("Bienvenido, " + userLogged.getName());
    }
}
