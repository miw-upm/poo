package upm.appentrega2.services;

import org.junit.jupiter.api.Test;
import upm.appentrega2.DependencyInjector;
import upm.appentrega2.data.models.Article;
import upm.appentrega2.data.repositories.ArticleRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleServiceTest {

    private final ArticleService articleService;

    private final ArticleRepository articleRepository;

    public ArticleServiceTest() {
        DependencyInjector dependencyInjector = new DependencyInjector();
        this.articleService = dependencyInjector.getArticleService();
        this.articleRepository = dependencyInjector.getArticleRepository();
    }

    @Test
    void testCreate() {
        this.articleService.create(new Article("8400011122201", "art-1", new BigDecimal("1.1"), "prov-1"));
        assertTrue(articleRepository.findByBarcode("8400011122201").isPresent());
    }

    @Test
    void testCreateBarcodeException() {
        Article article = new Article("8412345123460", "error", BigDecimal.TEN, "error");
        assertThrows(RuntimeException.class, () -> this.articleService.create(article));
    }

    @Test
    void testFindAll() {
        assertTrue(this.articleService.findAll().size() >= 4);
    }

    @Test
    void testFindByTagName() {
        List<Article> articles = this.articleService.findByTagName("tag1");
        assertEquals(2, articles.size());
        for (Article article : articles) {
            assertTrue(List.of("8412345123450", "8412345123460").contains(article.getBarcode()));
        }
    }
}
