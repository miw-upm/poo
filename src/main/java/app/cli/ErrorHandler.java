package app.cli;

import app.cli.view.View;

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
