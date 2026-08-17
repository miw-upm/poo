package es.upm.poo.services.exceptions;

public class ConflictException extends RuntimeException {
    private static final String DESCRIPTION = "Atributo duplicado. Debiera ser único";

    public ConflictException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}