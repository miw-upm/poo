package upm.app.console.commands;

public class BadCommandException extends RuntimeException {
    private static final String DESCRIPTION = "Bad Command Exception. The number or type of command parameters is incorrect.";

    public BadCommandException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}