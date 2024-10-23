package upm.appentrega3.services.exceptions;

public class UnauthorizedException extends RuntimeException {
    private static final String DESCRIPTION = "No autorizado";

    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}