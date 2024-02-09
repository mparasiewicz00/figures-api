package pl.kurs.figures.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.figures.model.Figure;

@Service
public interface FigureService {
    Figure createShape(String type, double[] parameters);
}
