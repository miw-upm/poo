package es.upm.poo.cli.commands;

import es.upm.poo.services.exceptions.ConflictException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateUserTest extends CommandIntegrationTestSupport {

    @Test
    void testCreateUser() {
        String email = UUID.randomUUID() + "@upm.es";
        this.runCommand("create-user#Integration User," + email + ",25,false");

        assertFalse(this.dependencyInjector.getUserRepository().findByEmail(email).isEmpty());
    }

    @Test
    void testCreateUserWithDuplicatedEmail() {
        assertThrows(
                ConflictException.class,
                () -> this.runCommand(
                        "create-user#Duplicated User,alice.johnson@upm.es,30,true"));
    }

    @Test
    void testCreateUserWithInvalidEmail() {
        assertThrows(
                IllegalArgumentException.class,
                () -> this.runCommand(
                        "create-user#Invalid Email User,invalid-email,30,true"));
    }
}
