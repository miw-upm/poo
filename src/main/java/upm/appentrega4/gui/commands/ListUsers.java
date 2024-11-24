package upm.appentrega4.gui.commands;

import upm.appentrega4.gui.Command;
import upm.appentrega4.gui.View;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.services.UserService;

import java.util.List;

public class ListUsers implements Command {
    private final View view;
    private final UserService userService;

    public ListUsers(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public List<String> execute(String[] params) {
       return this.userService.findAll().map(userBd -> userBd.toString()).toList();
    }

    @Override
    public String name() {
        return "list-users";
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
        return "Lista todos los usuarios";
    }

}
