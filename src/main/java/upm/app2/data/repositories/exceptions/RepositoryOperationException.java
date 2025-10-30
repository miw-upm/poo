package upm.app2.data.repositories.exceptions;

public class RepositoryOperationException extends RuntimeException {
    private static final String DESCRIPTION = "Repository Operation Exception";

    public RepositoryOperationException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}