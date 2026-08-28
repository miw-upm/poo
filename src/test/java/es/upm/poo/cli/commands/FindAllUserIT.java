package es.upm.poo.cli.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FindAllUserTest extends CommandIntegrationTestSupport {

    @Test
    void testFindAllSeededUsers() {
        this.runCommand("find-all-user");
        assertTrue(this.output().contains("Alice Johnson"));
    }
}
