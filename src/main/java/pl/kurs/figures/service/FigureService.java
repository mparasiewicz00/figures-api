package pl.kurs.figures.service;

import org.springframework.stereotype.Service;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.dto.FigureDTO;


import java.util.List;

@Service
public interface FigureService {
    FigureDTO createFigure(CreateFigureCommand command);
    boolean isValidParameters(String type, List<Double> parameters);

}
