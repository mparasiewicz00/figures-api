package pl.kurs.figures.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("CIRCLE")
public class Circle extends Figure implements Serializable {

    private double radius;








}
