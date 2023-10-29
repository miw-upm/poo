package upm.app.data.repositories.repositories_map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upm.app.data.models.Article;
import upm.app.data.repositories.ArticleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryMapTest {

    private ArticleRepository articleRepository;

    @BeforeEach
    public void setUp() {
        ShopSeeder shopSeeder = new ShopSeeder();
        shopSeeder.seed();
        this.articleRepository = shopSeeder.getArticleRepository();
    }

    @Test
    public void testCreateAndRead() {
        Optional<Article> article = this.articleRepository.read(2);
        assertTrue(this.articleRepository.read(2).isPresent());
        assertEquals("art2", article.get().getSummary());
    }

    @Test
    public void testFindByBarcode() {
        Optional<Article> foundArticle = articleRepository.findByBarcode("8412345123450");
        assertTrue(foundArticle.isPresent());
        assertEquals("art1", foundArticle.get().getSummary());
    }

    @Test
    public void testFindByBarcodeNotFound() {
        assertFalse(articleRepository.findByBarcode("0000000000000").isPresent());
    }

    @Test
    public void testUpdate() {
        Article article = this.articleRepository.read(2).get();
        article.setSummary("Updated Product B");
        articleRepository.update(article);

        Optional<Article> retrievedArticle = articleRepository.read(2);
        assertTrue(retrievedArticle.isPresent());
        assertEquals("Updated Product B", retrievedArticle.get().getSummary());
    }

    @Test
    public void testDelete() {
        Article createdArticle = this.articleRepository.create(
                new Article("6665554443332", "Not", new BigDecimal("15.99"), LocalDate.now(), "Not"));
        this.articleRepository.deleteById(createdArticle.getId());
        assertFalse(this.articleRepository.read(createdArticle.getId()).isPresent());
    }
}
