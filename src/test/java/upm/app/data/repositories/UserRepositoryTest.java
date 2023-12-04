package upm.app.data.repositories;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import upm.app.console.version2.DependencyInjector;
import upm.app.data.models.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private final UserRepository userRepository = DependencyInjector.getDependencyInjector().getUserRepository();

    @Test
    void testFindAll (){
        LogManager.getLogger(this.getClass()).debug("findAll: " + this.userRepository.findAll());
    }

    @Test
    void testCreateAndRead() {
        Optional<User> user = this.userRepository.read(1);
        assertTrue(user.isPresent());
        assertEquals("user1", user.get().getName());
    }

    @Test
    void testFindByMobile() {
        Optional<User> foundUser = userRepository.findByMobile(666000662);
        assertTrue(foundUser.isPresent());
        assertEquals("user3", foundUser.get().getName());
    }

    @Test
    void testFindByMobileNotFound() {
        assertFalse(userRepository.findByMobile(0).isPresent());
    }

    @Test
    void testUpdate() {
        User user = this.userRepository.read(3).get();
        String oldName = user.getName();
        user.setName("Updated user C");
        userRepository.update(user);

        Optional<User> retrievedUser = userRepository.read(3);
        assertTrue(retrievedUser.isPresent());
        assertEquals("Updated user C", retrievedUser.get().getName());

        user.setName(oldName);
        userRepository.update(user);
    }

    @Test
    void testDelete() {
        User createdUser = this.userRepository.create(new User(666555666, "Not", "Not"));
        this.userRepository.deleteById(createdUser.getId());
        assertFalse(this.userRepository.read(createdUser.getId()).isPresent());
    }
}
