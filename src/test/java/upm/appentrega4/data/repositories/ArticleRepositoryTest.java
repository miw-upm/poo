package upm.appentrega4.data.repositories;

import org.junit.jupiter.api.Test;
import upm.appentrega4.DependencyInjector;
import upm.appentrega4.data.models.Article;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryTest {

    private final ArticleRepository articleRepository = DependencyInjector.getInstance().getArticleRepository();

    @Test
    void testFindByBarcode() {
        Optional<Article> dbUser = articleRepository.findByBarcode("8412345123420");
        assertTrue(dbUser.isPresent());
        assertEquals("art2", dbUser.get().getSummary());
    }

    @Test
    void testFindByMobileNotFound() {
        assertFalse(articleRepository.findByBarcode("0123456789123").isPresent());
    }

}
