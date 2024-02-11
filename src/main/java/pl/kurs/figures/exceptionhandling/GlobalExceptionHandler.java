package pl.kurs.figures.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kurs.figures.exceptions.InvalidFigureParametersException;
import pl.kurs.figures.exceptions.ShapeNotExistException;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFigureParametersException.class)
    public ResponseEntity<ExceptionResponseDTO> handleInvalidFigureParametersException(InvalidFigureParametersException e) {
        ExceptionResponseDTO response = new ExceptionResponseDTO(
                List.of(e.getMessage()),
                "BAD_REQUEST",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ShapeNotExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleShapeNotExistException(ShapeNotExistException e) {
        ExceptionResponseDTO response = new ExceptionResponseDTO(
                List.of(e.getMessage()),
                "NOT_FOUND",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
