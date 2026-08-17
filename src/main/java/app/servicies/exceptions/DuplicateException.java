package app.servicies.exceptions;

public class DuplicateException extends RuntimeException {
    private static final String DESCRIPTION = "Duplicated attribute";

    public DuplicateException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
