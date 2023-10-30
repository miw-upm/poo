package upm.app.data.repositories.repositories_map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upm.app.data.models.ShoppingCart;
import upm.app.data.repositories.ArticleRepository;
import upm.app.data.repositories.ShoppingCartRepository;
import upm.app.data.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingCartRepositoryMapTest {
    private ShoppingCartRepository shoppingCartRepository;
    private ArticleRepository articleRepository;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        ShopSeeder shopSeeder = new ShopSeeder();
        shopSeeder.seed();
        this.shoppingCartRepository = shopSeeder.getShoppingCartRepository();
        this.articleRepository = shopSeeder.getArticleRepository();
        this.userRepository = shopSeeder.getUserRepository();
    }

    @Test
    public void testCreateAndRead() {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepository.read(1);
        assertTrue(shoppingCart.isPresent());
        assertEquals(2, shoppingCart.get().getArticleItems().size());
        assertEquals("user1", shoppingCart.get().getUser().getName());
    }

}
