package pl.kurs.figures.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.io.Serializable;


@Entity
@DiscriminatorValue("RECTANGLE")
public class Rectangle extends Figure implements Serializable {

    private double firstSideLength;
    private double secondSideLength;
    private double area;
    private double perimeter;




}
