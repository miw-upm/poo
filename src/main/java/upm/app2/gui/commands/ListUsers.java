package upm.app2.gui.commands;

import upm.app2.gui.Command;
import upm.app2.gui.fx.dialogs.EntityListDialog;
import upm.app2.services.UserService;

public class ListUsers implements Command {
    private final UserService userService;

    public ListUsers(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String name() {
        return "👥 List Users";
    }

    @Override
    public void prepareAndExecute() {
        new EntityListDialog(this.name(), this.userService.findAll());
    }

}
