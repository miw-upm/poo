package upm.introduction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AgeTest {

    @Test
    void testIsAdult() {
        assertTrue(new Age(18).isAdult());
    }

    @Test
    void testNotIsAdult() {
        assertFalse(new Age(17).isAdult());
    }

    @Test
    void testIsChild() {
        assertTrue(new Age(12).isChild());
    }

    @Test
    void testNotIsChild() {
        assertFalse(new Age(13).isChild());
    }

    @Test
    void testIsTeenager() {
        assertTrue(new Age(13).isTeenager());
        assertTrue(new Age(17).isTeenager());
    }

    @Test
    void testNotIsTeenager() {
        assertFalse(new Age(12).isTeenager());
        assertFalse(new Age(18).isTeenager());
    }
}

