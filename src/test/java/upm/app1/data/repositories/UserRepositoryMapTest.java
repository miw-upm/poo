package upm.app1.data.repositories;

import org.junit.jupiter.api.Test;
import upm.app1.data.models.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryMapTest {

    private final UserRepositoryMap userRepository;

    public UserRepositoryMapTest() {
        this.userRepository = new UserRepositoryMap();
        new Seeder(this.userRepository).seed();
    }

    @Test
    void testCreateAndRead() {
        Optional<User> dbUser = this.userRepository.read(1);
        assertTrue(dbUser.isPresent());
        assertEquals("uno", dbUser.get().getName());
        assertEquals("Calle 1", dbUser.get().getAddress());
    }

    @Test
    void testUpdate() {
        User user = this.userRepository.read(1).orElseThrow();
        String oldName = user.getName();
        user.setName("Updated user 2");
        this.userRepository.update(user.getId(), user);

        User retrievedUser = userRepository.read(1).orElseThrow();
        assertEquals("Updated user 2", retrievedUser.getName());

        retrievedUser.setName(oldName);
        this.userRepository.update(1, retrievedUser);
    }

    @Test
    void testDelete() {
        User user = this.userRepository.create(new User(666000003, "user-3", "address-3"));
        this.userRepository.deleteById(user.getId());
        assertFalse(this.userRepository.read(user.getId()).isPresent());
    }

    @Test
    void testFindByMobile() {
        Optional<User> dbUser = userRepository.findByMobile(666000663);
        assertTrue(dbUser.isPresent());
        assertEquals("tres", dbUser.get().getName());
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
