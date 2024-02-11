package pl.kurs.figures.service;

import pl.kurs.figures.exceptions.ShapeNotExistException;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.Rectangle;
import pl.kurs.figures.model.Square;

public class FigureFactory {
    public static Figure createFigure(String type, double... parameters) {
        return switch (type.toUpperCase()) {
            case "CIRCLE" -> new Circle(parameters[0]);
            case "SQUARE" -> new Square(parameters[0]);
            case "RECTANGLE" -> new Rectangle(parameters[0], parameters[1]);
            default -> throw new ShapeNotExistException("Wrong shape passed");
        };
    }

}

