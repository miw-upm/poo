package es.upm.poo.data.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testConstructor() {
        User user = new User("Alice Johnson", "alice.johnson@upm.es", 19, true);

        assertNull(user.getId());
        assertEquals("Alice Johnson", user.getName());
        assertEquals("alice.johnson@upm.es", user.getEmail());
        assertEquals(19, user.getAge());
        assertTrue(user.getActive());
    }

    @Test
    void testDefaultActive() {
        User user = new User("Alice Johnson", null, null, null);
        assertTrue(user.getActive());
    }

    @Test
    void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> new User(null, null, null, null));
        assertThrows(IllegalArgumentException.class, () -> new User(" ", null, null, null));
    }

    @Test
    void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> new User("Alice Johnson", "invalid-email", null, null));
    }

    @Test
    void testInvalidAge() {
        assertThrows(IllegalArgumentException.class, () -> new User("Alice Johnson", null, -1, null));
    }

    @Test
    void testEqualsById() {
        User firstUser = new User("Alice Johnson", null, null, null);
        User secondUser = new User("Marco Rossi", null, null, null);
        firstUser.setId(1L);
        secondUser.setId(1L);

        assertEquals(firstUser, secondUser);
        assertEquals(firstUser.hashCode(), secondUser.hashCode());
    }

    @Test
    void testTransientUsersAreNotEquals() {
        User firstUser = new User("Alice Johnson", null, null, null);
        User secondUser = new User("Alice Johnson", null, null, null);

        assertNotEquals(firstUser, secondUser);
    }
}
