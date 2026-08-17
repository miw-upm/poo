package es.upm.poo.cli.commands;

import es.upm.poo.cli.Command;
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
    public void execute(String[] params) {
        User updatedUser = this.userService.update(Long.valueOf(params[0]),
                new User(params[1],
                        this.optionalParam(params, 2),
                        this.optionalIntegerParam(params, 3),
                        this.optionalBooleanParam(params, 4)));
        this.view.showItem(updatedUser);
    }

    private String optionalParam(String[] params, int index) {
        if (params.length <= index) {
            return null;
        }
        return params[index];
    }

    private Integer optionalIntegerParam(String[] params, int index) {
        if (params.length <= index) {
            return null;
        }
        return Integer.valueOf(params[index]);
    }

    private Boolean optionalBooleanParam(String[] params, int index) {
        if (params.length <= index) {
            return null;
        }
        return Boolean.valueOf(params[index]);
    }
}
