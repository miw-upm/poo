package upm.appentrega3.data.repositories;

import org.junit.jupiter.api.Test;
import upm.appentrega3.DependencyInjector;
import upm.appentrega3.data.models.Article;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryTest {

    private final ArticleRepository articleRepository = new DependencyInjector().getArticleRepository();

    @Test
    void testFindByBarcode() {
        Optional<Article> dbUser = articleRepository.findByBarcode("8412345123460");
        assertTrue(dbUser.isPresent());
        assertEquals("art2", dbUser.get().getSummary());
    }

    @Test
    void testFindByMobileNotFound() {
        assertFalse(articleRepository.findByBarcode("0123456789123").isPresent());
    }

}
