package pl.kurs.figures.model;

import org.springframework.stereotype.Service;
import pl.kurs.figures.exceptions.ShapeNotExistException;
public class ShapeFactory {
    public static Figure createShape(String type, double... parameters) {
        switch (type.toUpperCase()) {
            case "CIRCLE":
                validateParametersCount(type, parameters, 1);
                return new Circle(parameters[0]);
            case "SQUARE":
                validateParametersCount(type, parameters, 1);
                return new Square(parameters[0]);
            case "RECTANGLE":
                validateParametersCount(type, parameters, 2);
                return new Rectangle(parameters[0], parameters[1]);
            default:
                throw new ShapeNotExistException("Wrong shape passed");
        }
    }

    private static void validateParametersCount(String type, double[] parameters, int expected) {
        if (parameters.length != expected) {
            throw new IllegalArgumentException("Invalid number of parameters for " + type + ": expected " + expected);
        }
}
}
