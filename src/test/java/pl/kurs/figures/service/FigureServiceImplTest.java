package pl.kurs.figures.service;

import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.command.FigureSearchCriteria;
import pl.kurs.figures.dto.CircleDTO;
import pl.kurs.figures.dto.FigureDTO;
import pl.kurs.figures.exceptions.InvalidFigureParametersException;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.FigureView;
import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.repository.FigureViewRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Mock
    private FigureViewRepository figureViewRepository;

    // createFigure()
    @Test
    @WithMockUser(username = "user")
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

        Exception exception = assertThrows(InvalidFigureParametersException.class, () -> figureService.createFigure(command));

        assertEquals("Type cannot be null", exception.getMessage());
    }

    @Test
    void createFigure_WithNullableParametersCount_ShouldThrowException() {
        CreateFigureCommand command = new CreateFigureCommand("CIRCLE", null);

        Exception exception = assertThrows(InvalidFigureParametersException.class, () -> figureService.createFigure(command));

        assertEquals("Parameters cannot be null", exception.getMessage());
    }


    //searchFigures
    @Test
    @WithMockUser(username = "ADMIN")
    void shouldSearchFiguresBasedOnCriteria() {
        FigureSearchCriteria criteria = new FigureSearchCriteria();
        criteria.setCreatedBy("user");
        Pageable pageable = PageRequest.of(0, 10);

        List<FigureView> figureViews = new ArrayList<>();
        figureViews.add(new FigureView(1L, "CIRCLE", "admin", LocalDateTime.now(), LocalDateTime.now(), "user", 314.15, 62.83, 10.0, null, null, null));
        Page<FigureView> expectedPage = new PageImpl<>(figureViews);
        Predicate predicate = FigureViewQueryCreator.createPredicate(criteria);

        when(figureViewRepository.findAll(predicate, pageable)).thenReturn(expectedPage);

        assertAll(
                () -> assertThat(figureService.getFiguresCreatedByUserPage(criteria, pageable)).isEqualTo(expectedPage),
                () -> verify(figureViewRepository).findAll(predicate, pageable)
        );
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
    @WithMockUser(username = "user")
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