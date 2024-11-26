package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.User;
import upm.appentrega4.gui.Command;
import upm.appentrega4.gui.Controller;
import upm.appentrega4.services.UserService;

import java.util.List;

public class Login implements Command {
    private final UserService userService;
    private final Controller controller;

    public Login(Controller controller, UserService userService) {
        this.controller = controller;
        this.userService = userService;
    }

    @Override
    public String name() {
        return "login";
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
    public List<Object> execute(String[] params) {
        User userLogged = this.userService.login(Integer.valueOf(params[0]), params[1]);
        this.controller.setUser(userLogged);
        return List.of("Bienvenido, " + userLogged.getName());
    }
}
