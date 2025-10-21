package upm.app2.presentation.cli.commands;

import upm.app2.presentation.cli.Command;
import upm.app2.presentation.view.View;
import upm.app2.services.UserService;

import java.util.List;

public class ListUsers implements Command {
    private final View view;
    private final UserService userService;

    public ListUsers(View view, UserService userService) {
        this.view = view;
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
    public String helpMessage() {
        return "Lista todos los usuarios";
    }

    @Override
    public void execute(String[] params) {
        this.view.showList("Usuarios", this.userService.findAll());
    }
}
