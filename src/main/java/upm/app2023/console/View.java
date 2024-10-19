package upm.app2023.console;

public class View {
    public static final String COMMAND = "app>";
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BACKGROUND_BLACK = "\u001B[40m";
    public static final String BACKGROUND_RED = "\u001B[41m";
    public static final String BACKGROUND_GREEN = "\u001B[42m";
    public static final String BACKGROUND_YELLOW = "\u001B[43m";
    public static final String BACKGROUND_BLUE = "\u001B[44m";
    public static final String BACKGROUND_PURPLE = "\u001B[45m";
    public static final String BACKGROUND_CYAN = "\u001B[46m";
    public static final String BACKGROUND_WHITE = "\u001B[47m";
    public static final String COPY_RIGHT = "\u00A9";
    public static final String RETURN = "\r";

    public void show(String message) {
        System.out.println(View.CYAN + "   - " + message + View.RESET);
    }

    public void showBold(String message) {
        System.out.println(View.RED + "  " + message + "  " + View.RESET);
    }

    public void showError(String message) {
        System.out.println(View.BACKGROUND_RED + View.BLACK + "  " + message + "  " + View.RESET);
    }

    public void showCommand() {
        System.out.print(COMMAND);
    }
}