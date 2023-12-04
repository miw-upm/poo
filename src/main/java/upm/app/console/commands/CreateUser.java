package upm.app.console.commands;

import upm.app.console.Command;
import upm.app.console.View;
import upm.app.console.version2.CommandNames;
import upm.app.data.models.User;
import upm.app.services.UserService;

public class CreateUser implements Command {

    private static final String NAME = "create-user";
    private static final String HELP = ":<mobile>;<name>;<address> Se crea un usuario";
    private final UserService userService;
    private final View view;

    public CreateUser(UserService userService, View view) {
        this.userService = userService;
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        if (values.length != 3) {
            throw new IllegalArgumentException(CommandNames.CREATE_USER.getHelp());
        }
        User createdUser = this.userService.create(new User(Integer.valueOf(values[0]), values[1], values[2]));
        this.view.show(createdUser.toString());
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String help() {
        return NAME + HELP;
    }
}
