package pl.kurs.figures.service;

import pl.kurs.figures.exceptions.ShapeNotExistException;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.Rectangle;
import pl.kurs.figures.model.Square;

import java.util.List;

public class FigureFactory {
    public static Figure createFigure(String type, List<Double> parameters) {
        return switch (type.toUpperCase()) {
            case "CIRCLE" -> new Circle(parameters.get(0));
            case "SQUARE" -> new Square(parameters.get(0));
            case "RECTANGLE" -> new Rectangle(parameters.get(0), parameters.get(1));
            default -> throw new ShapeNotExistException("Wrong shape passed");
        };
    }

}

