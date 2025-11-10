package upm.app2.presentation.cli.commands;

import upm.app2.data.models.ShoppingCart;
import upm.app2.data.models.ArticleItemCreation;
import upm.app2.presentation.cli.Command;
import upm.app2.presentation.view.View;
import upm.app2.services.ShoppingCartService;

import java.math.BigDecimal;
import java.util.List;

public class CreateShoppingCart implements Command {
    private final View view;
    private final ShoppingCartService cartService;

    public CreateShoppingCart(View view, ShoppingCartService cartService) {
        this.view = view;
        this.cartService = cartService;
    }

    @Override
    public String name() {
        return "create-cart";
    }

    @Override
    public List<String> params() {
        return List.of("userId", "articleId", "amount", "discount");
    }

    @Override
    public String helpMessage() {
        return "Se crea un carro de la compra";
    }

    @Override
    public void execute(String[] params) {
        ShoppingCart createdCart = this.cartService.create(Integer.valueOf(params[0]), new ArticleItemCreation(
                Integer.valueOf(params[1]), Integer.valueOf(params[2]), new BigDecimal(params[3])));
        this.view.showItem(createdCart);
    }
}
