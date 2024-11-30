package upm.appentrega4.data.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void testValidMobile() {
       User user = new User(123_456_789, "666", "name", "C/...");
        Assertions.assertEquals(123_456_789, user.getMobile());
    }

    @Test
    void testInvalidMobileLessThan9Digits() {
        Assertions.assertThrows(RuntimeException.class, () -> new User(123, "666", "error", "error"));
    }

    @Test
    void testInvalidMobileMoreThan9Digits() {
        Assertions.assertThrows(RuntimeException.class, () -> new User(123_456_789_0, "666", "error", "error"));
    }

    @Test
    void testNegativeMobile() {
        Assertions.assertThrows(RuntimeException.class, () -> new User(-123_456_789, "666", "error", "error"));
    }
}
