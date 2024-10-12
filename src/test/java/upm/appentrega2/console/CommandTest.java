package upm.appentrega2.console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    void testFromValueUser() {
        assertEquals(Command.CREATE_USER, Command.fromValue("create-user"));
        assertEquals(Command.LIST_USERS, Command.fromValue("list-users"));
    }

    @Test
    void testFromInvalidCommand() {
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> Command.fromValue("invalid-command"));
    }

    @Test
    void testFromValueHelp() {
        assertEquals(Command.HELP, Command.fromValue("help"));
    }

    @Test
    void testFromValueExit() {
        assertEquals(Command.EXIT, Command.fromValue("exit"));
    }

    @Test
    void testGetParamsCreateUser() {
        assertArrayEquals(new String[]{"mobile", "name", "address"}, Command.CREATE_USER.getParams());
    }

    @Test
    void testGetUserHelp() {
        assertEquals("create-user:mobile,name,address. Se crea un usuario.", Command.CREATE_USER.getHelp());
    }
}

