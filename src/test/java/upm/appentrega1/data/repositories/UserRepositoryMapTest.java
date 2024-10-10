package upm.appentrega1.data.repositories;

import org.junit.jupiter.api.Test;
import upm.appentrega1.data.models.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryMapTest {

    private final UserRepositoryMap userRepository = new UserRepositoryMap();

    @Test
    void testCreateAndRead() {
        User user = this.userRepository.create(new User(666000001, "user-1", "address-1"));
        Optional<User> dbUser = this.userRepository.read(user.getId());
        assertTrue(dbUser.isPresent());
        assertEquals("user-1", dbUser.get().getName());
        assertEquals("address-1", dbUser.get().getAddress());
    }

    @Test
    void testUpdate() {
        User user = this.userRepository.create(new User(666000002, "user-2", "address-2"));
        User dbUser = this.userRepository.read(user.getId()).get();
        dbUser.setName("Updated user 2");
        this.userRepository.update(dbUser);

        Optional<User> retrievedUser = userRepository.read(user.getId());
        assertTrue(retrievedUser.isPresent());
        assertEquals("Updated user 2", retrievedUser.get().getName());
    }

    @Test
    void testDelete() {
        User user = this.userRepository.create(new User(666000003, "user-3", "address-3"));
        this.userRepository.deleteById(user.getId());
        assertFalse(this.userRepository.read(user.getId()).isPresent());
    }

    @Test
    void testFindByMobile() {
        User user = this.userRepository.create(new User(666000004, "user-4", "address-4"));
        Optional<User> dbUser = userRepository.findByMobile(666000004);
        assertTrue(dbUser.isPresent());
        assertEquals("user-4", dbUser.get().getName());
    }

    @Test
    void testFindByMobileNotFound() {
        assertFalse(userRepository.findByMobile(0).isPresent());
    }

    @Test
    void testFindAll() {
        this.userRepository.create(new User(666000005, "user-5", "address-5"));
        this.userRepository.create(new User(666000006, "user-6", "address-6"));
        List<User> list = this.userRepository.findAll();
        assertTrue(list.size() >= 2);
    }
}
