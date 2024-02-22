package pl.kurs.figures.service;


import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.command.FigureSearchCriteria;
import pl.kurs.figures.dto.CircleDTO;
import pl.kurs.figures.dto.FigureDTO;
import pl.kurs.figures.dto.RectangleDTO;
import pl.kurs.figures.dto.SquareDTO;
import pl.kurs.figures.exceptions.FigureNotFoundException;
import pl.kurs.figures.exceptions.InvalidFigureParametersException;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.Rectangle;
import pl.kurs.figures.model.Square;
import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.repository.FigureViewRepository;
import pl.kurs.figures.model.FigureView;
import pl.kurs.figures.security.model.User;
import pl.kurs.figures.security.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FigureServiceImpl implements FigureService {

    private final FigureRepository figureRepository;
    private final ModelMapper modelMapper;
    private final FigureFactory figureFactory;
    private final FigureViewRepository figureViewRepository;
    private final UserRepository userRepository;

    @Override
    public FigureDTO createFigure(CreateFigureCommand command) {

        String type = String.valueOf(Optional.ofNullable(command.getType())
                .orElseThrow(() -> new InvalidFigureParametersException("Type cannot be null")));

        List<Double> parameters = Optional.ofNullable(command.getParameters())
                .orElseThrow(() -> new InvalidFigureParametersException("Parameters cannot be null"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!isValidType(type)) {
            throw new InvalidFigureParametersException("Unsupported figure type: " + type);
        }

        if (!areParametersValid(parameters)) {
            throw new InvalidFigureParametersException("Parameters must be greater than 0");
        }

        if (!areParametersCountValid(type, parameters)) {
            throw new InvalidFigureParametersException("Invalid number of parameters for type: " + type);
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        Figure figure = figureFactory.createFigure(type, parameters);
        figure.setUser(user);
        figureRepository.save(figure);

        //Adding figure to List of user figures
        user.addFigure(figure);
        userRepository.save(user);

        return mapToDTO(figure);
    }

    @Override
    public Page<FigureView> searchFigures(FigureSearchCriteria criteria, Pageable pageable) {
        Predicate predicate = FigureViewQueryCreator.createPredicate(criteria);
        return figureViewRepository.findAll(predicate, pageable);
    }

    public List<FigureDTO> getFiguresCreatedByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user.getFigures().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public FigureDTO getFigure(Long figureId, String username) {
        Figure figure = figureRepository.findById(figureId)
                .orElseThrow(() -> new FigureNotFoundException("Figure not found"));

        if (!figure.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You do not have permission to access this figure");
        }

        return mapToDTO(figure);
    }


    @Override
    public boolean areParametersValid(List<Double> parameters) {
        return parameters.stream().noneMatch(parameter -> parameter == null || parameter <= 0.0);
    }

    @Override
    public boolean areParametersCountValid(String type, List<Double> parameters) {
        return switch (type.toUpperCase()) {
            case "RECTANGLE" -> parameters.size() == 2;
            case "SQUARE", "CIRCLE" -> parameters.size() == 1;
            default -> false;
        };
    }

    @Override
    public boolean isValidType(String type) {
        return List.of("RECTANGLE", "SQUARE", "CIRCLE").contains(type.toUpperCase());
    }

    private FigureDTO mapToDTO(Figure figure) {
        return switch (figure.getType().toUpperCase()) {
            case "CIRCLE" -> mapToCircleDTO((Circle) figure);
            case "SQUARE" -> mapToSquareDTO((Square) figure);
            case "RECTANGLE" -> mapToRectangleDTO((Rectangle) figure);
            default -> throw new AssertionError("Unsupported figure type: " + figure.getType());
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

