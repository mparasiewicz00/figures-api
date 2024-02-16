package pl.kurs.figures.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.exceptions.InvalidFigureParametersException;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.repository.FigureRepository;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FigureServiceImplTest {

    @Mock
    private FigureRepository figureRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FigureServiceImpl figureService;

    @BeforeEach
    void setUp() {

    }
    @Test
    void createFigure_Circle_CorrectParameters() {

    }

    @Test
    void isValidType_ShouldReturnTrueForSupportedType() {
        assertTrue(figureService.isValidType("CIRCLE"));
    }

    @Test
    void isValidType_ShouldReturnFalseForUnsupportedType() {
        assertFalse(figureService.isValidType("TRIANGLE"));
    }

    @Test
    void isValidType_shouldThrowExceptionWhenTypeIsNotValid() {
        CreateFigureCommand command = new CreateFigureCommand("UNKNOWN", List.of(1.0));

        InvalidFigureParametersException exception = assertThrows(
                InvalidFigureParametersException.class,
                () -> figureService.createFigure(command)
        );

        assertTrue(exception.getMessage().contains("Unsupported figure type"));
    }

    @Test
    void isValidParameters_ShouldReturnTrueForCorrectParameters() {

    }

    @Test
    void isValidParameters_ShouldReturnFalseForIllegalNumberOfParameters() {

    }

    @Test
    void isValidParameters_ShouldReturnFalseForNullableParameters() {

    }

    @Test
    void isValidParameters_ShouldReturnFalseNegativeParameters() {

    }



}