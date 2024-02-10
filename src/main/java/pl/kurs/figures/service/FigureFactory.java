package pl.kurs.figures.service;

import pl.kurs.figures.exceptions.ShapeNotExistException;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.Rectangle;
import pl.kurs.figures.model.Square;

public class FigureFactory {
    public static Figure createFigure(String type, double... parameters) {
        return switch (type.toUpperCase()) {
            case "CIRCLE" -> {
                validateParametersCount(type, parameters, 1);
                yield new Circle(parameters[0]);
            }
            case "SQUARE" -> {
                validateParametersCount(type, parameters, 1);
                yield new Square(parameters[0]);
            }
            case "RECTANGLE" -> {
                validateParametersCount(type, parameters, 2);
                yield new Rectangle(parameters[0], parameters[1]);
            }
            default -> throw new ShapeNotExistException("Wrong shape passed");
        };
    }

    private static void validateParametersCount(String type, double[] parameters, int expected) {
        if (parameters.length != expected) {
            throw new IllegalArgumentException("Invalid number of parameters for " + type + ": expected " + expected);
        }
}
}
