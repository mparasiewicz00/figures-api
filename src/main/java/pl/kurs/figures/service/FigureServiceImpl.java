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
import java.util.Optional;

@Service
@AllArgsConstructor
public class FigureServiceImpl implements FigureService {

    private final FigureRepository figureRepository;
    private final ModelMapper modelMapper;

    @Override
    public FigureDTO createFigure(CreateFigureCommand command) {
        double [] parameters = Optional.ofNullable(command.getParameters())
                .filter(p -> isValidParameters(command.getType(), p))
                .orElseThrow(() -> new InvalidFigureParametersException("Invalid parameters passed"));

        Figure figure = FigureFactory.createFigure(command.getType(), parameters);

        Figure savedShape = figureRepository.save(figure);

        return modelMapper.map(savedShape, FigureDTO.class);
    }


    //Checking if parameters passed are not null or equals 0.0 and checking if number of parameters to passed shape is correct
    @Override
    public boolean isValidParameters(String type, double[] parameters) {
        //Primitive elements of array can't be null, then we only have to check if any of elements equals 0.0
        boolean zeroOrNullParameter = Arrays.stream(parameters)
                .anyMatch(p1 -> p1 == 0);

        if (zeroOrNullParameter) {
            throw new InvalidFigureParametersException("Invalid parameters passed");
        }

        return switch (type.toUpperCase()) {
            case "RECTANGLE" -> parameters.length == 2;
            case "SQUARE", "CIRCLE" -> parameters.length == 1;
            default -> false;
        };
    }


}
