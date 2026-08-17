package es.upm.poo.cli.commands;

import es.upm.poo.cli.Command;
import es.upm.poo.cli.CommandLineInterface;
import es.upm.poo.cli.CommandParams;

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
    public void execute(CommandParams params) {
        // Nothing to do, it never gets executed
    }

}
