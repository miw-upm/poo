package upm.appentrega2.console;

public class ErrorHandler {
    private final CommandLineInterface commandLineInterface;
    private final View view;

    public ErrorHandler(CommandLineInterface commandLineInterface, View view) {
        this.commandLineInterface = commandLineInterface;
        this.view = view;
        this.view.showBold("App. " + this.getClass().getPackageName() + "  " + upm.appentrega3.console.View.COPY_RIGHT + "UPM.ETSISI.POO");
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
