package upm.appentrega2.data.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void testValidMobile() {
        upm.appentrega1.data.models.User user = new upm.appentrega1.data.models.User(123_456_789, "name", "C/...");
        Assertions.assertEquals(123_456_789, user.getMobile());
    }

    @Test
    public void testInvalidMobileLessThan9Digits() {
        Assertions.assertThrows(RuntimeException.class, () -> new upm.appentrega1.data.models.User(123, "error", "error"));
    }

    @Test
    public void testInvalidMobileMoreThan9Digits() {
        Assertions.assertThrows(RuntimeException.class, () -> new upm.appentrega1.data.models.User(123_456_789_0, "error", "error"));
    }

    @Test
    public void testNegativeMobile() {
        Assertions.assertThrows(RuntimeException.class, () -> new User(-123_456_789, "error", "error"));
    }
}
