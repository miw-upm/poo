package upm.app.data.repositories.repositories_map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upm.app.data.models.Article;
import upm.app.data.repositories.ArticleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleRepositoryMapTest {

    Article article;
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setUp() {
        this.articleRepository = new ArticleRepositoryMap();
        this.article = this.articleRepository.create(new Article("9876543210987", "Product A", new BigDecimal("19.99"), LocalDate.now(), "Provider A"));
        this.articleRepository.create(new Article("1234567890123", "Product B", new BigDecimal("12.99"), LocalDate.now(), "Provider B"));
    }

    @Test
    public void testCreate() {
        Article createdArticle = articleRepository.create(new Article("6663336663336", "Product B", new BigDecimal("12.99"), LocalDate.now(), "Provider B"));
        assertNotNull(createdArticle.getId());
    }

    @Test
    public void testFindByBarcode() {
        Optional<Article> foundArticle = articleRepository.findByBarcode("9876543210987");
        assertTrue(foundArticle.isPresent());
        assertEquals("Product A", foundArticle.get().getSummary());

        Optional<Article> notFoundArticle = articleRepository.findByBarcode("0000000000000");
        assertFalse(notFoundArticle.isPresent());
    }

    @Test
    public void testFindByBarcodeNotFound() {
        Optional<Article> notFoundArticle = articleRepository.findByBarcode("0000000000000");
        assertFalse(notFoundArticle.isPresent());
    }

    @Test
    public void testRead() {
        Optional<Article> retrievedArticle = articleRepository.read(this.article.getId());
        assertTrue(retrievedArticle.isPresent());
        assertEquals(this.article, retrievedArticle.get());
    }

    @Test
    public void testUpdate() {
        this.article.setSummary("Updated Product A");
        Article updatedArticle = articleRepository.update(this.article);

        Optional<Article> retrievedArticle = articleRepository.read(updatedArticle.getId());
        assertTrue(retrievedArticle.isPresent());
        assertEquals("Updated Product A", retrievedArticle.get().getSummary());
    }

    @Test
    public void testDelete() {
        Article createdArticle = this.articleRepository.create(new Article("6665554443332", "Not", new BigDecimal("15.99"), LocalDate.now(), "Not"));
        this.articleRepository.deleteById(createdArticle.getId());
        Optional<Article> retrievedArticle = this.articleRepository.read(createdArticle.getId());
        assertFalse(retrievedArticle.isPresent());
    }
}
