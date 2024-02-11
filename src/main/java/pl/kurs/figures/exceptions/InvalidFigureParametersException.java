package pl.kurs.figures.exceptions;

public class InvalidFigureParametersException extends RuntimeException {

    public InvalidFigureParametersException(String message) {
        super(message);
    }
    public InvalidFigureParametersException(String message, Throwable cause) {
            super(message, cause);
    }

}
