package upm.app.data.repositories;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import upm.app.console.version2.DependencyInjector;
import upm.app.data.models.ArticleItem;
import upm.app.data.models.ShoppingCart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartRepositoryTest {
    private final ShoppingCartRepository shoppingCartRepository = DependencyInjector.getDependencyInjector().getShoppingCartRepository();
    private final UserRepository userRepository = DependencyInjector.getDependencyInjector().getUserRepository();

    private final ArticleRepository articleRepository = DependencyInjector.getDependencyInjector().getArticleRepository();

    @Test
    void testFindAll() {
        LogManager.getLogger(this.getClass()).debug("findAll: " + this.shoppingCartRepository.findAll());
    }

    @Test
    void testCreateAndRead() {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepository.read(1);
        assertTrue(shoppingCart.isPresent());
        assertEquals(2, shoppingCart.get().getArticleItems().size());
        assertEquals("user1", shoppingCart.get().getUser().getName());
    }

    @Test
    void testDelete() {
        ShoppingCart shoppingCart = new ShoppingCart(this.userRepository.read(1).orElseThrow(), LocalDateTime.now());
        shoppingCart = this.shoppingCartRepository.create(shoppingCart);
        this.shoppingCartRepository.deleteById(shoppingCart.getId());
        assertFalse(this.shoppingCartRepository.read(shoppingCart.getId()).isPresent());
    }

    @Test
    void testUpdate() {
        ShoppingCart shoppingCart = this.shoppingCartRepository.read(2).get();
        shoppingCart.add(new ArticleItem(this.articleRepository.read(1).get(), 1, BigDecimal.TEN));
        this.shoppingCartRepository.update(shoppingCart);

        Optional<ShoppingCart> retrieverShoppingCart = this.shoppingCartRepository.read(2);
        assertTrue(retrieverShoppingCart.isPresent());
        assertEquals(3, retrieverShoppingCart.get().getArticleItems().size());
        //TODO BD modificada
    }

}
