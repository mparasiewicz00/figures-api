package pl.kurs.figures.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.io.Serializable;


@Entity
@DiscriminatorValue("RECTANGLE")
public class Rectangle extends Figure implements Serializable {

    @Column(name = "firstSideLength", nullable = false)
    private double firstSideLength;

    @Column(name = "secondSideLength", nullable = false)
    private double secondSideLength;





}
