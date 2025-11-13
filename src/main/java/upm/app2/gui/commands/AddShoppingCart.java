package upm.app2.gui.commands;

import javafx.scene.layout.BorderPane;
import upm.app2.data.models.ArticleItemCreationDto;
import upm.app2.data.models.ShoppingCart;
import upm.app2.gui.Command;
import upm.app2.gui.fx.components.Status;
import upm.app2.gui.fx.components.entries.EntryComboBox;
import upm.app2.gui.fx.components.entries.EntryIntegerSpinner;
import upm.app2.gui.fx.components.entries.GenericContentArea;
import upm.app2.gui.fx.components.entries.KeyValue;
import upm.app2.services.ArticleService;
import upm.app2.services.ShoppingCartService;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

public class AddShoppingCart implements Command {
    private final BorderPane contentArea;
    private final Status status;
    private final ArticleService articleService;
    private final ShoppingCartService shoppingCartService;

    public AddShoppingCart(BorderPane contentArea, Status status, ArticleService articleService, ShoppingCartService shoppingCartService) {
        this.contentArea = contentArea;
        this.status = status;
        this.articleService = articleService;
        this.shoppingCartService = shoppingCartService;
    }


    @Override
    public String name() {
        return "🛒➕ Add to Cart";
    }

    @Override
    public void prepareAndExecute() {
        List<KeyValue<Integer>> carts = this.shoppingCartService.findAll().stream()
                .map(cart -> new KeyValue<>(cart.getId(), cart.getUser().getName() + " (" + cart.getCreationDate() + ")"))
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
            this.status.successful(createdCart.toString());
        };
        GenericContentArea content = new GenericContentArea(consumer, this.status);
        content.addEntryField("shoppingCart", new EntryComboBox<>("Select an shopping cart", carts));
        content.addEntryField("article", new EntryComboBox<>("Select an article", articles));
        content.addEntryField("amount", new EntryIntegerSpinner(1, 100));
        content.addEntryField("discount", new EntryIntegerSpinner(0, 100));
        this.contentArea.setCenter(content);
    }

}
