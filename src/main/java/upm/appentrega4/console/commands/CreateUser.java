package upm.appentrega4.console.commands;

import upm.appentrega4.console.Command;
import upm.appentrega4.console.View;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.User;
import upm.appentrega4.services.UserService;

import java.util.List;

public class CreateUser implements Command {
    private final View view;
    private final UserService userService;

    public CreateUser(View view, UserService userService) {
        this.view = view;
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
    public void execute(String[] params) {
        User createdUser = this.userService.create(new User(Integer.valueOf(params[0]), params[1], params[2], params[3]));
        this.view.show(createdUser.toString());
    }
}
