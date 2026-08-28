package es.upm.poo.cli.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelpTest extends CommandIntegrationTestSupport {

    @Test
    void testHelpForRegisteredCommands() {
        this.runCommand("help");
        assertTrue(this.output().contains("create-user#name,[email],[age],[active]"));
    }
}
