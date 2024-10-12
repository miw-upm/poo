package upm.appentrega1.services;

import org.junit.jupiter.api.Test;
import upm.appentrega1.data.models.User;
import upm.appentrega1.data.repositories.UserRepositoryMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private final UserService userService = new UserService(new UserRepositoryMap());

    @Test
    void testCreateMobileException() {
        User user = new User(666000660, "...", "...");
        this.userService.create(user);
        assertThrows(RuntimeException.class, () -> this.userService.create(user));
    }

    @Test
    void testFindAll() {
        this.userService.create(new User(666000005, "user-5", "address-5"));
        this.userService.create(new User(666000006, "user-6", "address-6"));
        List<User> list = this.userService.findAll();
        assertTrue(list.size() >= 2);
    }
}
