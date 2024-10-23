package upm.appentrega3.console.exceptions;

public class BadRequestException extends RuntimeException {
    private static final String DESCRIPTION = "Petición incorrecta";

    public BadRequestException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}