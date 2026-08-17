package app.cli.commands;

import app.cli.Command;
import app.cli.CommandLineInterface;

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
    public List<String> obligatoryParams() {
        return List.of();
    }

    @Override
    public List<String> optionalParams() {
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
