package es.upm.poo.cli.commands;

import es.upm.poo.cli.Command;
import es.upm.poo.cli.view.View;
import es.upm.poo.data.models.User;
import es.upm.poo.servicies.UserService;

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
    public List<String> obligatoryParams() {
        return List.of("name");
    }

    @Override
    public List<String> optionalParams() {
        return List.of("email", "age", "active");
    }

    @Override
    public String helpMessage() {
        return "Se crea un usuario";
    }

    @Override
    public void execute(String[] params) {
        User createdUser = this.userService.create(
                new User(params[0],
                        this.optionalParam(params, 1),
                        this.optionalIntegerParam(params, 2),
                        this.optionalBooleanParam(params, 3)));
        this.view.showItem(createdUser);
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
