package pl.kurs.figures.service;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.dto.FigureDTO;
import pl.kurs.figures.exceptions.InvalidFigureParametersException;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.repository.FigureRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FigureServiceImpl implements FigureService {

    private final FigureRepository figureRepository;
    private final ModelMapper modelMapper;

    @Override
    public FigureDTO createFigure(CreateFigureCommand command) {
        List<Double> parameters = Optional.ofNullable(command.getParameters())
                .filter(p -> isValidParameters(command.getType(), p))
                .orElseThrow(() -> new InvalidFigureParametersException("Invalid number of parameters"));


        Figure figure = FigureFactory.createFigure(command.getType(), parameters);
        Figure savedShape = figureRepository.save(figure);

        FigureDTO figureDTO = modelMapper.map(savedShape, FigureDTO.class);

        figureDTO.setArea(savedShape.calculateArea());
        figureDTO.setPerimeter(savedShape.calculatePerimeter());

        return  figureDTO;
    }


    //Checking if parameters passed are not null or equals 0.0 and checking if number of parameters to passed shape is correct
    @Override
    public boolean isValidParameters(String type, List<Double> parameters) {

        boolean zeroOrNullParameter = parameters.stream()
                .anyMatch(parameter -> parameter == null || parameter == 0.0);

        if (zeroOrNullParameter) {
            throw new InvalidFigureParametersException("Invalid parameters passed");
        }

        return switch (type.toUpperCase()) {
            case "RECTANGLE" -> parameters.size() == 2;
            case "SQUARE", "CIRCLE" -> parameters.size() == 1;
            default -> false;
        };
    }


}
