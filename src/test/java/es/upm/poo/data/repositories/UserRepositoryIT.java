package es.upm.poo.data.repositories;

import es.upm.poo.configuration.CliDependencyInjector;
import es.upm.poo.data.model.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryIT {
    private final UserRepository userRepository = CliDependencyInjector.getInstance().getUserRepository();

    @Test
    void testFindByEmail() {
        Optional<User> user = this.userRepository.findByEmail("alice.johnson@upm.es");

        assertTrue(user.isPresent());
        assertEquals("Alice Johnson", user.get().getName());
        assertEquals("alice.johnson@upm.es", user.get().getEmail());
    }

    @Test
    void testFindByEmailNotFound() {
        Optional<User> user = this.userRepository.findByEmail("unknown@upm.es");

        assertTrue(user.isEmpty());
    }

    @Test
    void testFindByEmailNull() {
        Optional<User> user = this.userRepository.findByEmail(null);

        assertTrue(user.isEmpty());
    }
}
