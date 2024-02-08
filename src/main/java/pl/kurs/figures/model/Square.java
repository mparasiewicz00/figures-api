package pl.kurs.figures.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.io.Serializable;


@Entity
@DiscriminatorValue("SQUARE")
public class Square extends Figure implements Serializable {

    @Column(name = "sideLength", nullable = false)
    private double sideLength;




}
