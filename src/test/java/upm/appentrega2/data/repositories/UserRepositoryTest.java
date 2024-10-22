package upm.appentrega2.data.repositories;

import org.junit.jupiter.api.Test;
import upm.appentrega2.DependencyInjector;
import upm.appentrega2.data.models.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private final UserRepository userRepository = new DependencyInjector().getUserRepository();

    @Test
    void testCreateAndRead() {
        User user = this.userRepository.create(new User(666000001, "666", "user-1", "address-1"));
        Optional<User> dbUser = this.userRepository.read(user.getId());
        assertTrue(dbUser.isPresent());
        assertEquals("user-1", dbUser.get().getName());
        assertEquals("address-1", dbUser.get().getAddress());
    }

    @Test
    void testUpdate() {
        User dbUser = this.userRepository.read(1).get();
        dbUser.setName("Updated user 1");
        this.userRepository.update(dbUser);

        Optional<User> retrievedUser = userRepository.read(1);
        assertTrue(retrievedUser.isPresent());
        assertEquals("Updated user 1", retrievedUser.get().getName());

        dbUser.setName("user1");
        this.userRepository.update(dbUser);
    }

    @Test
    void testDelete() {
        User user = this.userRepository.create(new User(666000002, "666", "user-2", "address-2"));
        this.userRepository.deleteById(user.getId());
        assertFalse(this.userRepository.read(user.getId()).isPresent());
    }

    @Test
    void testFindByMobile() {
        Optional<User> dbUser = userRepository.findByMobile(666000661);
        assertTrue(dbUser.isPresent());
        assertEquals("user2", dbUser.get().getName());
    }

    @Test
    void testFindByMobileNotFound() {
        assertFalse(userRepository.findByMobile(0).isPresent());
    }

    @Test
    void testFindAll() {
        List<User> list = this.userRepository.findAll();
        assertTrue(list.size() >= 4);
    }
}
