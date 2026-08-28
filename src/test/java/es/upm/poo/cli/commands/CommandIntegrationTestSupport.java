package es.upm.poo.cli.commands;

import es.upm.poo.cli.CommandLineInterface;
import es.upm.poo.configuration.CliDependencyInjector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

abstract class CommandIntegrationTestSupport {
    protected final CliDependencyInjector dependencyInjector = CliDependencyInjector.getInstance();
    protected final CommandLineInterface commandLineInterface = this.dependencyInjector.getCommandLineInterface();

    private final PrintStream standardOutput = System.out;
    private ByteArrayOutputStream output;

    @BeforeEach
    void redirectOutput() {
        this.output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.output, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void restoreOutput() {
        System.setOut(this.standardOutput);
    }

    protected boolean runCommand(String input) {
        try (Scanner scanner = new Scanner(input).useDelimiter("[#\\r\\n]")) {
            return this.commandLineInterface.runCommand(scanner);
        }
    }

    protected String output() {
        return this.output.toString(StandardCharsets.UTF_8);
    }
}
