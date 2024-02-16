package pl.kurs.figures.service;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.dto.CircleDTO;
import pl.kurs.figures.dto.FigureDTO;
import pl.kurs.figures.dto.RectangleDTO;
import pl.kurs.figures.dto.SquareDTO;
import pl.kurs.figures.exceptions.InvalidFigureParametersException;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.Rectangle;
import pl.kurs.figures.model.Square;
import pl.kurs.figures.repository.FigureRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FigureServiceImpl implements FigureService {

    private final FigureRepository figureRepository;
    private final ModelMapper modelMapper;

    @Override
    public FigureDTO createFigure(CreateFigureCommand command) {
        if (!isValidType(command.getType())) {
            throw new InvalidFigureParametersException("Unsupported figure type: " + command.getType());
        }

        List<Double> parameters = Optional.ofNullable(command.getParameters())
                .filter(p -> isValidParameters(command.getType(), p))
                .orElseThrow(() -> new InvalidFigureParametersException("Invalid number of parameters"));

        Figure figure = FigureFactory.createFigure(command.getType(), parameters);
        figureRepository.save(figure);

        return mapToDTO(figure);
    }

    /* Checking if user pass correct type of figure */
    @Override
    public boolean isValidType(String type) {
        return List.of("RECTANGLE", "SQUARE", "CIRCLE").contains(type.toUpperCase());
    }

    /* Checking if parameters passed are not null or equals 0.0 and checking if number of parameters to passed shape is correct */
    @Override
    public boolean isValidParameters(String type, List<Double> parameters) {
        boolean zeroOrNullParameter = parameters.stream()
                .anyMatch(parameter -> parameter == null || parameter <= 0.0);

        if (zeroOrNullParameter) {
            throw new InvalidFigureParametersException("Invalid parameters passed");
        }

        return switch (type.toUpperCase()) {
            case "RECTANGLE" -> parameters.size() == 2;
            case "SQUARE", "CIRCLE" -> parameters.size() == 1;
            default -> false; //
        };
    }

    private FigureDTO mapToDTO(Figure figure) {
        return switch (figure.getType().toUpperCase()) {
            case "CIRCLE" -> mapToCircleDTO((Circle) figure);
            case "SQUARE" -> mapToSquareDTO((Square) figure);
            case "RECTANGLE" -> mapToRectangleDTO((Rectangle) figure);
            default -> throw new InvalidFigureParametersException("Unsupported figure type: " + figure.getType());
        };
    }

    private CircleDTO mapToCircleDTO(Circle circle) {
        CircleDTO dto = modelMapper.map(circle, CircleDTO.class);
        dto.setRadius(circle.getRadius());
        dto.setArea(circle.calculateArea());
        dto.setPerimeter(circle.calculatePerimeter());
        return dto;
    }

    private RectangleDTO mapToRectangleDTO(Rectangle rectangle) {
        RectangleDTO dto = modelMapper.map(rectangle, RectangleDTO.class);
        dto.setFirst_side_length(rectangle.getFirstSideLength());
        dto.setSecond_side_length(rectangle.getSecondSideLength());
        dto.setArea(rectangle.calculateArea());
        dto.setPerimeter(rectangle.calculatePerimeter());
        return dto;
    }

    private SquareDTO mapToSquareDTO(Square square) {
        SquareDTO dto = modelMapper.map(square, SquareDTO.class);
        dto.setSide_length(square.getSideLength());
        dto.setArea(square.calculateArea());
        dto.setPerimeter(square.calculatePerimeter());
        return dto;
    }


}

