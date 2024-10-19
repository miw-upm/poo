package upm.app2023.services;

import org.junit.jupiter.api.Test;
import upm.app2023.DependencyInjector;
import upm.app2023.data.models.User;
import upm.app2023.services.exceptions.DuplicateException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

    private final UserService userService = DependencyInjector.getDependencyInjector().getUserService();

    @Test
    void testCreateMobileException() {
        assertThrows(DuplicateException.class,
                () -> this.userService.create(new User(666000660, "...", "...")));
    }
}
