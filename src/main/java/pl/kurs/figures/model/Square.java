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
@DiscriminatorValue("SQUARE")
public class Square extends Figure implements Serializable {

    @Column(name = "sideLength", nullable = false)
    private double sideLength;

    @Override
    public double calculateArea() {
        return sideLength * sideLength;
    }

    @Override
    public double calculatePerimeter() {
        return sideLength * 4;
    }
}
