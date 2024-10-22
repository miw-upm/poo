package upm.appentrega2.services.exceptions;

public class DuplicateException extends RuntimeException {
    private static final String DESCRIPTION = "Atributo duplicado. Debiera ser único";

    public DuplicateException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}