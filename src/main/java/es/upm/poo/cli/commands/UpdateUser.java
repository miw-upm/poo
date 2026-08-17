package es.upm.poo.cli.commands;

import es.upm.poo.cli.Command;
import es.upm.poo.cli.CommandParams;
import es.upm.poo.cli.view.View;
import es.upm.poo.data.models.User;
import es.upm.poo.services.UserService;

import java.util.List;

public class UpdateUser implements Command {
    private final View view;
    private final UserService userService;

    public UpdateUser(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public String name() {
        return "update-user";
    }

    @Override
    public List<String> obligatoryParams() {
        return List.of("id", "name");
    }

    @Override
    public List<String> optionalParams() {
        return List.of("email", "age", "active");
    }

    @Override
    public String helpMessage() {
        return "Actualiza un usuario";
    }

    @Override
    public void execute(CommandParams params) {
        User updatedUser = this.userService.update(params.getLong(0),
                new User(params.getString(1),
                        params.getString(2),
                        params.getInteger(3),
                        params.getBoolean(4)));
        this.view.showItem(updatedUser);
    }
}
