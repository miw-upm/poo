package upm.repositories;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import upm.app.console.version2.DependencyInjector;
import upm.app.data.models.Article;
import upm.app.data.repositories.ArticleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryTest {

    private final ArticleRepository articleRepository = DependencyInjector.getDependencyInjector().getArticleRepository();

    @Test
    void testFindAll (){
        LogManager.getLogger(this.getClass()).debug("findAll: " + this.articleRepository.findAll());
    }

    @Test
    void testCreateAndRead() {
        Optional<Article> article = this.articleRepository.read(2);
        assertTrue(article.isPresent());
        assertEquals("art2", article.get().getSummary());
    }

    @Test
    void testReadNotFound() {
        Optional<Article> article = this.articleRepository.read(666);
        assertFalse(article.isPresent());
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
        Article entity = new Article("6665554443332", "Not", new BigDecimal("15.99"), "Not");
        entity.setRegistrationDate(LocalDate.now());
        Article createdArticle = this.articleRepository.create(entity);
        this.articleRepository.deleteById(createdArticle.getId());
        assertFalse(this.articleRepository.read(createdArticle.getId()).isPresent());
    }
}
