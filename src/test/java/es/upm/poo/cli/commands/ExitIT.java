package es.upm.poo.cli.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExitTest extends CommandIntegrationTestSupport {

    @Test
    void testExit() {
        assertTrue(this.runCommand("exit"));
    }
}
