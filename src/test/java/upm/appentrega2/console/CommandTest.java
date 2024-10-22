package upm.appentrega2.console;

import org.junit.jupiter.api.Test;
import upm.appentrega2.data.models.Rol;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    void testFromValueUser() {
        assertEquals(Command.CREATE_USER, Command.fromValue("create-user", Rol.ADMIN));
        assertEquals(Command.LIST_USERS, Command.fromValue("list-users",Rol.ADMIN));
    }

    @Test
    void testFromInvalidCommand() {
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> Command.fromValue("invalid-command",Rol.ADMIN));
    }

    @Test
    void testFromValueHelp() {
        assertEquals(Command.HELP, Command.fromValue("help",Rol.ADMIN));
    }

    @Test
    void testFromValueExit() {
        assertEquals(Command.EXIT, Command.fromValue("exit",Rol.ADMIN));
    }

    @Test
    void testGetParamsCreateUser() {
        assertArrayEquals(new String[]{"mobile", "password", "name", "address"}, Command.CREATE_USER.getParams());
    }

    @Test
    void testGetUserHelp() {
        assertEquals("create-user:mobile,password,name,address. Se crea un usuario.", Command.CREATE_USER.getHelp(Rol.ADMIN));
    }
}

