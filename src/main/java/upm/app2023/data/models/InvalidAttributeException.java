package upm.app2023.data.models;

public class InvalidAttributeException extends RuntimeException {
    private static final String DESCRIPTION = "Invalid Attribute Exception. The attribute value is out of range.";

    public InvalidAttributeException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}