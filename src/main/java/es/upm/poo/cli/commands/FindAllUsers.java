package es.upm.poo.cli.commands;

import es.upm.poo.cli.Command;
import es.upm.poo.cli.CommandParams;
import es.upm.poo.cli.view.View;
import es.upm.poo.data.model.User;
import es.upm.poo.services.UserService;

import java.util.List;

public class FindAllUsers implements Command {
    private final View view;
    private final UserService userService;

    public FindAllUsers(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public String name() {
        return "find-all-users";
    }

    @Override
    public List<String> obligatoryParams() {
        return List.of();
    }

    @Override
    public List<String> optionalParams() {
        return List.of();
    }

    @Override
    public String helpMessage() {
        return "Lista todos los usuarios";
    }

    @Override
    public void execute(CommandParams params) {
        List<User> users = this.userService.findAll();
        this.view.showList("Users", users);
    }
}
