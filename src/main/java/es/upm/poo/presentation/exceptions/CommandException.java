package es.upm.poo.presentation.exceptions;

public class CommandException extends RuntimeException {
    private static final String DESCRIPTION = "Comando incorrecto";

    public CommandException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}