package upm.appentrega3.services;

import org.junit.jupiter.api.Test;
import upm.appentrega3.DependencyInjector;
import upm.appentrega3.data.models.User;
import upm.appentrega3.data.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService userService;

    private final UserRepository userRepository;

    public UserServiceTest() {
        this.userService = DependencyInjector.getInstance().getUserService();
        this.userRepository = DependencyInjector.getInstance().getUserRepository();
    }

    @Test
    void testCreate() {
        this.userService.create(new User(666000010, "666", "...", "..."));
        assertTrue(userRepository.findByMobile(666000010).isPresent());
    }

    @Test
    void testCreateMobileException() {
        User user = new User(666000660, "666", "...", "...");
        assertThrows(RuntimeException.class, () -> this.userService.create(user));
    }

    @Test
    void testFindAll() {
        assertTrue(this.userService.findAll().size() >= 4);
    }

    @Test
    void testLogin() {
        assertNotNull(this.userService.login(666000660, "666"));
    }

    @Test
    void testLoginMobileError() {
        assertThrows(RuntimeException.class, () -> this.userService.login(666, "666"));
    }

    @Test
    void testLoginPasswordError() {
        assertThrows(RuntimeException.class, () -> this.userService.login(666000660, "error"));
    }
}
