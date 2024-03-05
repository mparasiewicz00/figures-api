package pl.kurs.figures.exceptions;

public class EmptyTableException extends RuntimeException {

    public EmptyTableException(String message) {
        super(message);
    }

    public EmptyTableException(String message, Throwable cause) {
        super(message, cause);
    }
}
