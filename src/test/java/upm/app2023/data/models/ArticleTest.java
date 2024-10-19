package upm.app2023.data.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArticleTest {

    @Test
    void testBuilder() {
        Article article = new Article("0123456789012", "s", BigDecimal.TEN, null);
        assertEquals("0123456789012", article.getBarcode());
        assertEquals("s", article.getSummary());
        assertEquals(BigDecimal.TEN, article.getPrice());
        assertNull(article.getProvider());
    }
}
