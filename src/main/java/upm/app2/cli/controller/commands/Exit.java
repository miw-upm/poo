package upm.app2.cli.controller.commands;

import upm.app2.cli.controller.Command;
import upm.app2.cli.controller.CommandLineInterface;

import java.util.List;

public class Exit implements Command {

    @Override
    public String name() {
        return CommandLineInterface.EXIT;
    }

    @Override
    public List<String> params() {
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
