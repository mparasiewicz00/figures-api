package pl.kurs.figures.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("RECTANGLE")
public class Rectangle extends Figure implements Serializable {

    @Column(name = "firstSideLength")
    private double firstSideLength;

    @Column(name = "secondSideLength")
    private double secondSideLength;

    @Override
    public double calculateArea() {
        return firstSideLength * secondSideLength;
    }
    @Override
    public double calculatePerimeter() {
        return firstSideLength * 2 + secondSideLength * 2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(firstSideLength, rectangle.firstSideLength) == 0 && Double.compare(secondSideLength, rectangle.secondSideLength) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstSideLength, secondSideLength);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "firstSideLength=" + firstSideLength +
                ", secondSideLength=" + secondSideLength +
                '}';
    }
}
