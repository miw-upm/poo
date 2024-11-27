package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.gui.fx.View;
import upm.appentrega4.gui.fx.dialogs.EntityListDialog;
import upm.appentrega4.services.UserService;

import java.util.List;

public class ListUsers extends AbstractCommand {
    private final UserService userService;

    public ListUsers(UserService userService) {
        this.userService = userService;
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

    @Override
    public void execute() {
        new EntityListDialog(this.name(), this.userService.findAll()
                .map(Object.class::cast).toList());
        View.instance().getStatus().successful("Consulta realizada");
    }

}
