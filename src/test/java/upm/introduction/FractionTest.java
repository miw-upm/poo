package upm.introduction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FractionTest {
    @Test
    void testForbiddenDenominator() {
        assertThrows(RuntimeException.class, () -> new Fraction(1, 0));
    }
}
