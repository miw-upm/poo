package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.User;
import upm.appentrega4.gui.Command;
import upm.appentrega4.services.UserService;

import java.util.List;

public class CreateUser implements Command {
    private final UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String name() {
        return "create-user";
    }

    @Override
    public List<String> params() {
        return List.of("mobile", "password", "name", "address");
    }

    @Override
    public List<Rol> allowedRoles() {
        return Rol.all();
    }

    @Override
    public String helpMessage() {
        return "Se crea un usuario";
    }

    @Override
    public List<Object> execute(String[] params) {
        User createdUser = this.userService.create(new User(Integer.valueOf(params[0]), params[1], params[2], params[3]));
        return List.of(createdUser);
    }
}
