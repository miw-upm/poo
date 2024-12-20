package upm.doo.builder;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArticleTest {

    @Test
    void testBuilder() {

        Article article = Article.builder()
                .summary("s")
                .barcode("0123456789012")
                .price(BigDecimal.TEN)
                .build();
        assertEquals("0123456789012", article.getBarcode());
        assertEquals("s", article.getSummary());
        assertEquals(BigDecimal.TEN, article.getPrice());
        assertNull(article.getProvider());
    }
}
