package es.upm.poo.presentation.cli.commands;

import es.upm.poo.presentation.cli.Command;
import es.upm.poo.presentation.cli.CommandParams;
import es.upm.poo.presentation.view.View;
import es.upm.poo.data.model.User;
import es.upm.poo.services.UserService;

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
    public void execute(CommandParams params) {
        User newUser = new User(params.getString(0),
                params.getString(1), params.getInteger(2), params.getBoolean(3));
        User createdUser = this.userService.create(newUser);
        this.view.showItem(createdUser);
    }
}
