package upm.app2.presentation.cli.commands;

import upm.app2.data.models.User;
import upm.app2.presentation.cli.Command;
import upm.app2.presentation.view.View;
import upm.app2.services.UserService;

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
        return List.of("mobile", "name", "address");
    }

    @Override
    public String helpMessage() {
        return "Se crea un usuario";
    }

    @Override
    public void execute(String[] params) {
        User createdUser = this.userService.create(new User(Integer.valueOf(params[0]), params[1], params[2]));
        this.view.showItem(createdUser);
    }
}
