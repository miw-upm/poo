package upm.appentrega2.data.services;

import org.junit.jupiter.api.Test;
import upm.appentrega2.DependencyInjector;
import upm.appentrega2.data.models.User;
import upm.appentrega2.services.UserService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private final UserService userService = new DependencyInjector().getUserService();

    @Test
    void testCreateMobileException() {
        assertThrows(RuntimeException.class,
                () -> this.userService.create(new User(666000660, "...", "...")));
    }

    @Test
    void testFindAll() {
        assertTrue(this.userService.findAll().size() >= 4);
    }
}
