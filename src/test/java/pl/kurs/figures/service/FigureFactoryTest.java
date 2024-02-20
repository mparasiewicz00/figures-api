package pl.kurs.figures.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.exceptions.ShapeNotExistException;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class FigureFactoryTest {
    FigureFactory figureFactory = new FigureFactory();

    @Test
    void shouldCreateFigureWhenCorrectParametersPassed() {
        //given
        CreateFigureCommand command = new CreateFigureCommand("CIRCLE", List.of(20.0));

        //when
        Figure figure = figureFactory.createFigure(command.getType(), command.getParameters());

        //then
        assertAll(
                () -> assertEquals("CIRCLE", figure.getType()),
                () -> assertInstanceOf(Circle.class, figure)
        );
    }


    @Test
    void shouldThrowExceptionWhenIncorrectTypePassed() {
        CreateFigureCommand command = new CreateFigureCommand("TRIANGLE", List.of(10.0));

        Exception exception = assertThrows(ShapeNotExistException.class, () -> figureFactory.createFigure(command.getType(), command.getParameters()));
        assertTrue(exception.getMessage().contains("Wrong shape passed"));
    }

}