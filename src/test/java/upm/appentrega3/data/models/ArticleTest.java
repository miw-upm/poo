package upm.appentrega3.data.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

class ArticleTest {
    @Test
    void testValidBarcode() {
       Article article = new Article("8400011122200", "name", BigDecimal.TWO, "prov1");
        Assertions.assertEquals("8400011122200", article.getBarcode());
    }

    @Test
    void testInvalidBarcodeLessThan13Digits() {
        Assertions.assertThrows(RuntimeException.class, () -> new Article("0", "name", BigDecimal.TWO, "prov1"));
    }

    @Test
    void testInvalidMobileMoreThan13Digits() {
        Assertions.assertThrows(RuntimeException.class, () -> new Article("01234567891234", "name", BigDecimal.TWO, "prov1"));
    }

}
