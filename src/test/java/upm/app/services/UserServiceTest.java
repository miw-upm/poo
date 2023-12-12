package upm.app.services;

import org.junit.jupiter.api.Test;
import upm.app.DependencyInjector;
import upm.app.data.models.User;
import upm.app.services.exceptions.DuplicateException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

    private final UserService userService = DependencyInjector.getDependencyInjector().getUserService();

    @Test
    void testCreateMobileException() {
        assertThrows(DuplicateException.class,
                () -> this.userService.create(new User(666000660, "...", "...")));
    }
}
