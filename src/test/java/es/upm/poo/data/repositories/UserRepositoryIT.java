package es.upm.poo.data.repositories;

import es.upm.poo.configuration.CliDependencyInjector;
import es.upm.poo.data.models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryIT {
    private final UserRepository userRepository = CliDependencyInjector.getInstance().getUserRepository();

    @Test
    void testFindByEmail() {
        List<User> users = this.userRepository.findByEmail("alice.johnson@upm.es");

        assertEquals(1, users.size());
        assertEquals("Alice Johnson", users.getFirst().getName());
        assertEquals("alice.johnson@upm.es", users.getFirst().getEmail());
    }

    @Test
    void testFindByEmailNotFound() {
        List<User> users = this.userRepository.findByEmail("unknown@upm.es");

        assertTrue(users.isEmpty());
    }

    @Test
    void testFindByEmailNull() {
        List<User> users = this.userRepository.findByEmail(null);

        assertTrue(users.isEmpty());
    }
}
