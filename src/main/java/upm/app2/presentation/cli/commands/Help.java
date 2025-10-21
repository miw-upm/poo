package upm.app2.presentation.cli.commands;

import upm.app2.presentation.cli.Command;
import upm.app2.presentation.cli.CommandLineInterface;

import java.util.List;

public class Help implements Command {
    private final CommandLineInterface commandLineInterface;

    public Help(CommandLineInterface commandLineInterface) {
        this.commandLineInterface = commandLineInterface;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public List<String> params() {
        return List.of();
    }

    @Override
    public String helpMessage() {
        return "Muestra la ayuda";
    }

    @Override
    public void execute(String[] params) {
        this.commandLineInterface.help();
    }
}
