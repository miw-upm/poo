package upm.appentrega2.data.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArticleTest {
    @Test
    void testValidBarcode() {
        Article article = new Article("8400011122200", "name", 10.0, "prov1");
        Assertions.assertEquals("8400011122200", article.getBarcode());
    }

    @Test
    void testInvalidBarcodeLessThan13Digits() {
        Assertions.assertThrows(RuntimeException.class, () -> new Article("0", "name", 10.0, "prov1"));
    }

    @Test
    void testInvalidMobileMoreThan13Digits() {
        Assertions.assertThrows(RuntimeException.class, () -> new Article("01234567891234", "name", 10.0, "prov1"));
    }

}
