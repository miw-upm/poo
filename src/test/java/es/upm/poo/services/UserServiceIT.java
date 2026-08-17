package es.upm.poo.services;

import es.upm.poo.configuration.CliDependencyInjector;
import es.upm.poo.data.models.User;
import es.upm.poo.services.exceptions.ConflictException;
import es.upm.poo.services.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {
    private final UserService userService = CliDependencyInjector.getInstance().getUserService();

    @Test
    void testReadSeededUser() {
        User user = this.userService.read(1L);

        assertEquals(1L, user.getId());
        assertEquals("Alice Johnson", user.getName());
        assertEquals("alice.johnson@upm.es", user.getEmail());
        assertEquals(19, user.getAge());
        assertTrue(user.getActive());
    }

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

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.userService.read(999L));
    }

    @Test
    void testUpdate() {
        User updatedUser = this.userService.update(
                5L, new User("Updated User", "updated.user@upm.es", 43, true));

        assertEquals(5L, updatedUser.getId());
        assertEquals("Updated User", updatedUser.getName());
        assertEquals("updated.user@upm.es", updatedUser.getEmail());
        assertEquals(43, updatedUser.getAge());
        assertTrue(updatedUser.getActive());

        this.userService.update(5L, new User("Sofia Garcia", "sofia.garcia@upm.es", 34, false));
    }

    @Test
    void testUpdateDuplicatedEmail() {
        User user = new User("Duplicated Email", "alice.johnson@upm.es", 34, false);

        assertThrows(ConflictException.class, () -> this.userService.update(5L, user));
    }
}
