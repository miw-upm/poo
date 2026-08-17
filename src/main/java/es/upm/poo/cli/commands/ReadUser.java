package es.upm.poo.cli.commands;

import es.upm.poo.cli.Command;
import es.upm.poo.cli.view.View;
import es.upm.poo.data.models.User;
import es.upm.poo.services.UserService;

import java.util.List;

public class ReadUser implements Command {
    private final View view;
    private final UserService userService;

    public ReadUser(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public String name() {
        return "read-user";
    }

    @Override
    public List<String> obligatoryParams() {
        return List.of("id");
    }

    @Override
    public List<String> optionalParams() {
        return List.of();
    }

    @Override
    public String helpMessage() {
        return "Lee un usuario";
    }

    @Override
    public void execute(String[] params) {
        User user = this.userService.read(Long.valueOf(params[0]));
        this.view.showItem(user);
    }
}
