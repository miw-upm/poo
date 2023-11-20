package upm.app.services;

import org.junit.jupiter.api.Test;
import upm.app.DependencyInjector;
import upm.app.data.models.User;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

    private final UserService userService = DependencyInjector.getDependencyInjector().getUserService();

    @Test
    void testCreateMobileException() {
        assertThrows(IllegalArgumentException.class,
                () -> this.userService.create(new User(666000660, "...", "...")));
    }
}
