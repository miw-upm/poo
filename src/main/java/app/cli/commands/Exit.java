package app.cli.commands;

import app.cli.Command;
import app.cli.CommandLineInterface;

import java.util.List;

public class Exit implements Command {

    @Override
    public String name() {
        return CommandLineInterface.EXIT;
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
        return "Termina la ejecución";
    }

    @Override
    public void execute(String[] values) {
        // Nothing to do, it never gets executed
    }

}
