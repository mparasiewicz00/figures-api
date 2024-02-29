package pl.kurs.figures.exceptions;

public class PermissionDeniedException extends RuntimeException{
    public PermissionDeniedException(String message) {
        super(message);
    }
    public PermissionDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
