package es.upm.poo.services;

import es.upm.poo.configuration.CliDependencyInjector;
import es.upm.poo.data.model.User;
import es.upm.poo.services.exceptions.ConflictException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private final UserService userService = CliDependencyInjector.getInstance().getUserService();

    @Test
    void testFindAllSeededUsers() {
        List<User> users = this.userService.findAll();

        assertTrue(users.size() >= 5);
        assertFalse(users.isEmpty());
    }

    @Test
    void testCreate() {
        User user = this.userService.create(new User("Test User", null, 22, null));

        assertNotNull(user.getId());
        assertEquals("Test User", user.getName());
        assertEquals(22, user.getAge());
        assertTrue(user.getActive());
    }

    @Test
    void testCreateDuplicatedEmail() {
        User user = new User("Duplicated Email", "alice.johnson@upm.es", 23, true);

        assertThrows(ConflictException.class, () -> this.userService.create(user));
    }
}
