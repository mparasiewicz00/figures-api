package pl.kurs.figures.service;

import org.springframework.stereotype.Service;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.command.FigureSearchCriteria;
import pl.kurs.figures.dto.FigureDTO;
import pl.kurs.figures.model.FigureView;


import java.util.List;

@Service
public interface FigureService {
    FigureDTO createFigure(CreateFigureCommand command);

    List<FigureView> searchFigures(FigureSearchCriteria criteria);

    boolean areParametersValid(List<Double> parameters);

    boolean areParametersCountValid(String type, List<Double> parameters);

    boolean isValidType(String type);
}
