package upm.builder.advanced_solution;

import org.junit.jupiter.api.Test;
import upm.doo.builder.advanced_solution.Article;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArticleTest {

    @Test
    void testBuilder() {
        Article article = Article.builder().barcode("0123456789012").summary("s").price(BigDecimal.TEN).build();
        assertEquals("0123456789012", article.getBarcode());
        assertEquals("s", article.getSummary());
        assertEquals(BigDecimal.TEN, article.getPrice());
        assertNull(article.getProvider());
    }
}
