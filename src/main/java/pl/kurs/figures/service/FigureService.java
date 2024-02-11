package pl.kurs.figures.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.dto.FigureDTO;
import pl.kurs.figures.model.Figure;

@Service
public interface FigureService {
    FigureDTO createFigure(CreateFigureCommand command);
    boolean isValidParameters(String type, double[] parameters);
}
