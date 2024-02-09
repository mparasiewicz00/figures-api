package pl.kurs.figures.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("RECTANGLE")
public class Rectangle extends Figure implements Serializable {

    @Column(name = "firstSideLength", nullable = false)
    private double firstSideLength;

    @Column(name = "secondSideLength", nullable = false)
    private double secondSideLength;

    @Override
    public double calculateArea() {
        return firstSideLength * secondSideLength;
    }
    @Override
    public double calculatePerimeter() {
        return firstSideLength * 2 + secondSideLength * 2;
    }
}
