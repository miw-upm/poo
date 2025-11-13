package upm.app2.gui.commands;

import upm.app2.gui.Command;
import upm.app2.gui.fx.dialogs.EntityListDialog;
import upm.app2.services.ShoppingCartService;

public class ListShoppingCarts implements Command {
    private final ShoppingCartService shoppingCartService;

    public ListShoppingCarts(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public String name() {
        return "list-carts";
    }

    @Override
    public void prepareAndExecute() {
        new EntityListDialog(this.name(), this.shoppingCartService.findAll());
    }

}
