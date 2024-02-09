package pl.kurs.figures.service;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.figures.dto.FigureDTO;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.FigureFactory;
import pl.kurs.figures.repository.FigureRepository;

@Service
@AllArgsConstructor
public class FigureServiceImpl implements FigureService {

    private final FigureRepository figureRepository;
    private final ModelMapper modelMapper;

    @Override
    public FigureDTO createFigure(String type, double[] parameters) {
        Figure figure = FigureFactory.createFigure(type, parameters);

        Figure savedShape = figureRepository.save(figure);

        return modelMapper.map(savedShape, FigureDTO.class);
    }
}
