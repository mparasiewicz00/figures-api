package pl.kurs.figures.exceptions;

public class FigureNotFoundException extends RuntimeException {

    public FigureNotFoundException(String message) {
        super(message);
    }
    public FigureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
