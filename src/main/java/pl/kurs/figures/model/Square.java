package pl.kurs.figures.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.io.Serializable;


@Entity
@DiscriminatorValue("SQUARE")
public class Square extends Figure implements Serializable {

    private double sideLength;




}
