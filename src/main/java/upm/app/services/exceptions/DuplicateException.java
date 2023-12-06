package upm.app.services.exceptions;

public class DuplicateException extends RuntimeException {
    private static final String DESCRIPTION = "Duplicate Exception. The attribute value should be unique.";

    public DuplicateException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}