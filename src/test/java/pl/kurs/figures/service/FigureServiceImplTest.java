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
import pl.kurs.figures.dto.CircleDTO;
import pl.kurs.figures.dto.FigureDTO;
import pl.kurs.figures.dto.SquareDTO;
import pl.kurs.figures.exceptions.InvalidFigureParametersException;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
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

    @Mock
    private FigureFactory figureFactory;

    @InjectMocks
    private FigureServiceImpl figureService;

    // createFigure()
    @Test
    void createFigure_ShouldReturnFigureDTOWhenCorrectParametersPassed() {
        CreateFigureCommand command = new CreateFigureCommand("CIRCLE", List.of(5.0));
        Circle circle = new Circle(5.0);
        CircleDTO circleDTO = new CircleDTO();

        when(figureFactory.createFigure(eq("CIRCLE"), anyList())).thenReturn(circle);
        when(modelMapper.map(any(Circle.class), eq(CircleDTO.class))).thenReturn(circleDTO);
        when(figureRepository.save(any(Figure.class))).thenReturn(circle);

        FigureDTO resultDTO = figureService.createFigure(command);

        assertAll(
                () -> verify(figureFactory, atLeastOnce()).createFigure(command.getType(), command.getParameters()),
                () -> assertNotNull(resultDTO),
                () -> assertEquals(Math.PI * 25, resultDTO.getArea()),
                () -> assertEquals(2 * Math.PI * 5, resultDTO.getPerimeter())
        );
    }

    @Test
    void createFigure_WithNullableType_ShouldThrowException() {
        CreateFigureCommand command = new CreateFigureCommand(null, List.of(5.0));

        Exception exception = assertThrows(InvalidFigureParametersException.class, () -> {
            figureService.createFigure(command);
        });

        assertEquals("Type cannot be null", exception.getMessage());
    }

    @Test
    void createFigure_WithNullableParametersCount_ShouldThrowException() {
        CreateFigureCommand command = new CreateFigureCommand("CIRCLE", null);

        Exception exception = assertThrows(InvalidFigureParametersException.class, () -> {
            figureService.createFigure(command);
        });

        assertEquals("Parameters cannot be null", exception.getMessage());
    }

    // isValidType()

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

    // areParametersValid()
    @Test
    void areParametersValid_ShouldReturnTrueForPositiveAndNotNullParameters() {
        List<Double> parameters = List.of(10.0);
        assertTrue(() -> figureService.areParametersValid(parameters));
    }

    @Test
    void areParametersValid_ShouldReturnFalseForNegativeParameter() {
        List<Double> parameters = List.of( -5.0);
        assertFalse(() -> figureService.areParametersValid(parameters));
    }

    // areParametersCountValid()
    @Test
    void areParametersCountValid_ShouldReturnTrueForCorrectNumberOfParameters() {
        var commandFigureOne = mock(CreateFigureCommand.class);
        var commandFigureTwo = mock(CreateFigureCommand.class);

        doReturn("SQUARE").when(commandFigureOne).getType();
        doReturn(List.of(5.0)).when(commandFigureOne).getParameters();
        doReturn("RECTANGLE").when(commandFigureTwo).getType();
        doReturn(List.of(5.0, 10.0)).when(commandFigureTwo).getParameters();

        assertAll(
                () -> assertTrue(() -> figureService.areParametersCountValid(commandFigureOne.getType(), commandFigureOne.getParameters())),
                () -> assertTrue(() -> figureService.areParametersCountValid(commandFigureTwo.getType(), commandFigureTwo.getParameters()))
        );
    }

    @Test
    void areParametersCountValid_ShouldReturnFalseForIncorrectNumberOfParameters() {
        var commandFigureOne = mock(CreateFigureCommand.class);
        var commandFigureTwo = mock(CreateFigureCommand.class);

        doReturn("RECTANGLE").when(commandFigureOne).getType();
        doReturn(List.of(5.0)).when(commandFigureOne).getParameters();
        doReturn("CIRCLE").when(commandFigureTwo).getType();
        doReturn(List.of(5.0, 10.0)).when(commandFigureTwo).getParameters();

        assertAll(
                () -> assertFalse( () -> figureService.areParametersCountValid(commandFigureOne.getType(), commandFigureOne.getParameters())),
                () -> assertFalse( () -> figureService.areParametersCountValid(commandFigureTwo.getType(), commandFigureTwo.getParameters()))
        );

    }

}