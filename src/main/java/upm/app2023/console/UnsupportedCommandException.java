package upm.app2023.console;

public class UnsupportedCommandException extends RuntimeException {
    private static final String DESCRIPTION = "Unsupported Command Exception. The requested command does not exist.";

    public UnsupportedCommandException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}