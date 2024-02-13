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
@DiscriminatorValue("SQUARE")
public class Square extends Figure implements Serializable {

    @Column(name = "sideLength")
    private double sideLength;

    @Override
    public double calculateArea() {
        return sideLength * sideLength;
    }

    @Override
    public double calculatePerimeter() {
        return sideLength * 4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Square square = (Square) o;
        return Double.compare(sideLength, square.sideLength) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sideLength);
    }

    @Override
    public String toString() {
        return "Square{" +
                "sideLength=" + sideLength +
                '}';
    }
}
