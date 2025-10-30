package upm.app2.presentation.cli.commands;

import upm.app2.presentation.cli.Command;
import upm.app2.presentation.view.View;
import upm.app2.services.UserService;

import java.util.List;

public class FindUserMobilesByCartGreater100 implements Command {
    private final View view;
    private final UserService userService;

    public FindUserMobilesByCartGreater100(View view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public String name() {
        return "find-user-mobiles-by-cart-greater-100";
    }

    @Override
    public List<String> params() {
        return List.of();
    }

    @Override
    public String helpMessage() {
        return "Buscar los móviles de los usuarios que han realizado una compra superior a 100€";
    }

    @Override
    public void execute(String[] params) {
        List<Integer> mobiles = this.userService.findUserMobilesByCartGreater100();
        this.view.showList("Moviles", mobiles);
    }
}
