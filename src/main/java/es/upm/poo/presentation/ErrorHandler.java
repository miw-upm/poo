package es.upm.poo.presentation;

import es.upm.poo.presentation.cli.CommandLineInterface;
import es.upm.poo.presentation.view.View;

public class ErrorHandler {

    public void handlesErrors(CommandLineInterface commandLineInterface, View view) {
        view.showTitle("App UPM");
        boolean exit = false;
        while (!exit) {
            try {
                exit = commandLineInterface.runCommands();
            } catch (Exception e) {
                view.showError("ERROR (" + e.getClass().getSimpleName() + ") >>> " + e.getMessage());
            }
        }
        view.showTitle("Hasta pronto!");
    }
}
