package upm.appentrega3.console.commands;

import upm.appentrega3.console.Command;
import upm.appentrega3.console.View;
import upm.appentrega3.data.models.Rol;
import upm.appentrega3.data.models.User;
import upm.appentrega3.services.UserService;

import java.util.List;

public class CreateUser implements Command {

    private static final List<String> PARAMS = List.of("mobile", "password", "name", "address");
    private static final List<Rol> ALLOWED_ROLES = Rol.all();
    private final UserService userService;
    private final View view;

    public CreateUser(UserService userService, View view) {
        this.userService = userService;
        this.view = view;
    }

    @Override
    public void execute(String[] params) {
        User createdUser = this.userService.create(new User(Integer.valueOf(params[0]), params[1], params[2], params[3]));
        this.view.show(createdUser.toString());
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
}
