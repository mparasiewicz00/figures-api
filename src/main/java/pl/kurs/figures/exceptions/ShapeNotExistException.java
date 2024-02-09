package pl.kurs.figures.exceptions;

public class ShapeNotExistException extends RuntimeException{

    public ShapeNotExistException(String message) {
        super(message);
    }
    public ShapeNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
