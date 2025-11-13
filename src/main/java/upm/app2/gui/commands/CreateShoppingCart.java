package upm.app2.gui.commands;

import upm.app2.data.models.ArticleItemCreationDto;
import upm.app2.data.models.ShoppingCart;
import upm.app2.gui.Command;
import upm.app2.gui.fx.GraphicalUserInterfaceFX;
import upm.app2.gui.fx.components.entries.EntryComboBox;
import upm.app2.gui.fx.components.entries.EntryIntegerSpinner;
import upm.app2.gui.fx.components.entries.GenericContentArea;
import upm.app2.gui.fx.components.entries.KeyValue;
import upm.app2.services.ArticleService;
import upm.app2.services.ShoppingCartService;
import upm.app2.services.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

public class CreateShoppingCart implements Command {
    private final GraphicalUserInterfaceFX view;
    private final UserService userService;
    private final ArticleService articleService;
    private final ShoppingCartService shoppingCartService;

    public CreateShoppingCart(GraphicalUserInterfaceFX view, UserService userService, ArticleService articleService, ShoppingCartService shoppingCartService) {
        this.view = view;
        this.userService = userService;
        this.articleService = articleService;
        this.shoppingCartService = shoppingCartService;
    }


    @Override
    public String name() {
        return "create-cart";
    }

    @Override
    public void prepareAndExecute() {
        List<KeyValue<Integer>> users = this.userService.findAll().stream()
                .map(user -> new KeyValue<>(user.getId(), user.getName()))
                .toList();
        List<KeyValue<Integer>> articles = this.articleService.findAll()
                .map(article -> new KeyValue<>(article.getId(), article.getSummary()))
                .toList();

        Consumer<List<String>> consumer = params -> {
            ShoppingCart createdCart = this.shoppingCartService.create(Integer.valueOf(params.getFirst()),
                    new ArticleItemCreationDto(
                            Integer.valueOf(params.get(1)),
                            Integer.valueOf(params.get(2)),
                            new BigDecimal(params.get(3))
                    )
            );
            this.view.getStatus().successful(createdCart.toString());
        };
        GenericContentArea content = new GenericContentArea(consumer, this.view.getStatus());

        content.addEntryField("user", new EntryComboBox<>("Select an user", users));
        content.addEntryField("article", new EntryComboBox<>("Select an article", articles));
        content.addEntryField("amount", new EntryIntegerSpinner(1, 100));
        content.addEntryField("discount", new EntryIntegerSpinner(0, 100));
        this.view.setContentArea(content);
    }

}
