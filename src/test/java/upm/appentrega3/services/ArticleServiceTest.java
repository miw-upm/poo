package upm.appentrega3.services;

import org.junit.jupiter.api.Test;
import upm.appentrega3.DependencyInjector;
import upm.appentrega3.data.models.Article;
import upm.appentrega3.data.repositories.ArticleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleServiceTest {

    private final ArticleService articleService;

    private final ArticleRepository articleRepository;

    public ArticleServiceTest() {
        this.articleService = DependencyInjector.getInstance().getArticleService();
        this.articleRepository = DependencyInjector.getInstance().getArticleRepository();
    }

    @Test
    void testCreate() {
        Article article = new Article("8400011122201", "art-1", new BigDecimal("1.1"), "prov-1");
        article.setRegistrationDate(LocalDate.now());
        this.articleService.create(article);
        assertTrue(articleRepository.findByBarcode("8400011122201").isPresent());
    }

    @Test
    void testCreateBarcodeException() {
        Article article = new Article("8412345123460", "error", BigDecimal.TEN, "error");
        article.setRegistrationDate(LocalDate.now());
        assertThrows(RuntimeException.class, () -> this.articleService.create(article));
    }

    @Test
    void testFindAll() {
        assertTrue(this.articleService.findAll().toList().size() >= 4);
    }

    @Test
    void testFindByTagName() {
        List<Article> articles = this.articleService.findByTagName("tag1").toList();
        assertEquals(2, articles.size());
        for (Article article : articles) {
            assertTrue(List.of("8412345123410", "8412345123420").contains(article.getBarcode()));
        }
    }
}
