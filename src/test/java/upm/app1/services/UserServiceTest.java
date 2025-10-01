package upm.app1.services;

import org.junit.jupiter.api.Test;
import upm.app1.data.models.User;
import upm.app1.data.repositories.Seeder;
import upm.app1.data.repositories.UserRepositoryMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService userService;

    public UserServiceTest() {
        UserRepositoryMap userRepositoryMap = new UserRepositoryMap();
        this.userService = new UserService(userRepositoryMap);
        new Seeder(userRepositoryMap).seed();
    }

    @Test
    void testCreateMobileException() {
        User user = new User(666000661, "...", "...");
        assertThrows(RuntimeException.class, () -> this.userService.create(user));
    }

    @Test
    void testFindAll() {
        List<User> list = this.userService.findAll();
        assertFalse(list.isEmpty());
        for (User u : list) {
            if (666000663 == u.getMobile() && "tres".equals(u.getName()) && "Calle 3".equals(u.getAddress())
                    && u.getId() != null) {
                return;
            }
        }
        fail("El usuario creado no se encontró en la lista");
    }
}
