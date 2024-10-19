package upm.appentrega2.console;

public class View {
    public static final String COMMAND = "app>";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLACK = "\u001B[30m";
    public static final String BACKGROUND_RED = "\u001B[41m";
    public static final String COPY_RIGHT = "\u00A9";

    public void show(String message) {
        System.out.println(CYAN + "   - " + message + RESET);
    }

    public void showBold(String message) {
        System.out.println(RED + "  " + message + "  " + RESET);
    }

    public void showCommand(String name) {
        System.out.print(name + " " + COMMAND);
    }

    public void showCommand() {
        System.out.print(COMMAND);
    }

    public void showError(String message) {
        System.out.println(BACKGROUND_RED + BLACK + "  " + message + "  " + RESET);
    }
}