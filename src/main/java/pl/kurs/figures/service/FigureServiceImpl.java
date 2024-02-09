package pl.kurs.figures.service;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.ShapeFactory;
import pl.kurs.figures.repository.FigureRepository;

@Service
@AllArgsConstructor
public class FigureServiceImpl implements FigureService {

    private final FigureRepository figureRepository;
    private ModelMapper modelMapper;

    @Override
    public Figure createShape(String type, double[] parameters) {
        return ShapeFactory.createShape(type, parameters);
    }
}
