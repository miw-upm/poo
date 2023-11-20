package upm.app.data.repositories.repositories_map;

import org.junit.jupiter.api.Test;
import upm.app.DependencyInjector;
import upm.app.data.models.ShoppingCart;
import upm.app.data.repositories.ShoppingCartRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingCartRepositoryMapTest {
    private final ShoppingCartRepository shoppingCartRepository = DependencyInjector.getDependencyInjector().getShoppingCartRepository();

    @Test
    void testCreateAndRead() {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepository.read(1);
        assertTrue(shoppingCart.isPresent());
        assertEquals(2, shoppingCart.get().getArticleItems().size());
        assertEquals("user1", shoppingCart.get().getUser().getName());
    }

}
