package upm.app.data.repositories.repositories_map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upm.app.data.models.Article;
import upm.app.data.models.User;
import upm.app.data.repositories.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryMapTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        ShopSeeder shopSeeder = new ShopSeeder();
        shopSeeder.seed();
        this.userRepository = shopSeeder.getUserRepository();
    }

    @Test
    public void testCreateAndRead() {
        Optional<User> user = this.userRepository.read(1);
        assertTrue(this.userRepository.read(1).isPresent());
        assertEquals("user1", user.get().getName());
    }

    @Test
    public void testFindByMobile() {
        Optional<User> foundUser = userRepository.findByMobile(666000662);
        assertTrue(foundUser.isPresent());
        assertEquals("user3", foundUser.get().getName());
    }

    @Test
    public void testFindByMobileNotFound() {
        assertFalse(userRepository.findByMobile(000000000).isPresent());
    }

    @Test
    public void testUpdate() {
        User user = this.userRepository.read(3).get();
        user.setName("Updated user C");
        userRepository.update(user);

        Optional<User> retrievedUser = userRepository.read(3);
        assertTrue(retrievedUser.isPresent());
        assertEquals("Updated user C", retrievedUser.get().getName());
    }

    @Test
    public void testDelete() {
        User createdUser = this.userRepository.create(new User(666555666, "Not", "Not"));
        this.userRepository.deleteById(createdUser.getId());
        assertFalse( this.userRepository.read(createdUser.getId()).isPresent());
    }
}
