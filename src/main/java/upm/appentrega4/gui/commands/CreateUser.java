package upm.appentrega4.gui.commands;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.User;
import upm.appentrega4.gui.fx.GraphicalUserInterfaceFX;
import upm.appentrega4.gui.fx.dialogs.EntityListDialog;
import upm.appentrega4.services.UserService;

import java.util.List;

public class CreateUser extends AbstractCommand {
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
    public void execute() {
        this.preparedForm();
    }

    @Override
    public void executeAction(List<String> fields) {
        User createdUser = this.userService.create(
                new User(Integer.valueOf(fields.get(0)), fields.get(1), fields.get(2), fields.get(3)));
        GraphicalUserInterfaceFX.getInstance().getStatus().successful("Usuario creado correctamente");
        new EntityListDialog(this.name(), List.of(createdUser));
    }
}
