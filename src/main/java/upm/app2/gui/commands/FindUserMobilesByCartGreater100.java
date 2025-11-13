package upm.app2.gui.commands;

import upm.app2.gui.Command;
import upm.app2.gui.fx.dialogs.EntityListDialog;
import upm.app2.services.UserService;

public class FindUserMobilesByCartGreater100 implements Command {
    private final UserService userService;

    public FindUserMobilesByCartGreater100(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String name() {
        return "find-user-mobiles-by-cart-greater-100";
    }

    @Override
    public void prepareAndExecute() {
        new EntityListDialog(this.name(), this.userService.findUserMobilesByCartGreater100());
    }

}
