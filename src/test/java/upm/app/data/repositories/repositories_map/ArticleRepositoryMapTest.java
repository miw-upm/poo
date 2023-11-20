package upm.app.data.repositories.repositories_map;

import org.junit.jupiter.api.Test;
import upm.app.DependencyInjector;
import upm.app.data.models.Article;
import upm.app.data.repositories.ArticleRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryMapTest {

    private final ArticleRepository articleRepository = DependencyInjector.getDependencyInjector().getArticleRepository();

    @Test
    void testCreateAndRead() {
        Optional<Article> article = this.articleRepository.read(2);
        assertTrue(article.isPresent());
        assertEquals("art2", article.get().getSummary());
    }

    @Test
    void testFindByBarcode() {
        Optional<Article> foundArticle = articleRepository.findByBarcode("8412345123450");
        assertTrue(foundArticle.isPresent());
        assertEquals("art1", foundArticle.get().getSummary());
    }

    @Test
    void testFindByBarcodeNotFound() {
        assertFalse(articleRepository.findByBarcode("0000000000000").isPresent());
    }

    @Test
    void testUpdate() {
        Article article = this.articleRepository.read(2).get();
        String oldSummary = article.getSummary();
        article.setSummary("Updated Product B");
        articleRepository.update(article);

        Optional<Article> retrievedArticle = articleRepository.read(2);
        assertTrue(retrievedArticle.isPresent());
        assertEquals("Updated Product B", retrievedArticle.get().getSummary());

        article.setSummary(oldSummary);
        articleRepository.update(article);
    }

    @Test
    void testDelete() {
        Article createdArticle = this.articleRepository.create(
                new Article("6665554443332", "Not", new BigDecimal("15.99"), "Not"));
        this.articleRepository.deleteById(createdArticle.getId());
        assertFalse(this.articleRepository.read(createdArticle.getId()).isPresent());
    }
}
