package upm.appentrega3.data.repositories;

import org.junit.jupiter.api.Test;
import upm.appentrega3.DependencyInjector;
import upm.appentrega3.data.models.ArticleItem;
import upm.appentrega3.data.models.ShoppingCart;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingCartRepositoryTest {

    private final ShoppingCartRepository shoppingCartRepository = DependencyInjector.getInstance().getShoppingCartRepository();
    private final ArticleRepository articleRepository = DependencyInjector.getInstance().getArticleRepository();


    @Test
    void testFindAll() {
        assertTrue(!this.shoppingCartRepository.findAll().isEmpty());
    }

    @Test
    void testUpdate() {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findAll().stream().findFirst().orElseThrow();
        int size = shoppingCart.getArticleItems().size();
        shoppingCart.add(new ArticleItem(this.articleRepository.findByBarcode("8412345123440").orElseThrow(), 1, BigDecimal.TEN));
        this.shoppingCartRepository.update(shoppingCart);
        assertEquals(size + 1, this.shoppingCartRepository.read(shoppingCart.getId()).orElseThrow().getArticleItems().size());
    }

    @Test
    void testDeleteById(){

    }

}
