package upm.app2023.console.version2;

import upm.app2023.console.View;

public class ErrorHandler {
    private final CommandLineInterface commandLineInterface;
    private final View view;

    public ErrorHandler(CommandLineInterface commandLineInterface, View view) {
        this.commandLineInterface = commandLineInterface;
        this.view = view;
        this.view.showBold("App Gestión de Planes Sociales (GPS). " + View.COPY_RIGHT + "UPM.ETSISI.POO");
    }

    public void handlesErrors() {
        boolean exit = false;
        while (!exit) {
            try {
                exit = this.commandLineInterface.runCommands();
            } catch (Exception e) {
                this.view.showError(">>> ERROR (" + e.getClass().getSimpleName() + ") >>> " + e.getMessage());
            }
        }
        this.view.showBold("Hasta pronto!");
    }
}
