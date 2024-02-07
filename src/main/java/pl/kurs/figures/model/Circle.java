package pl.kurs.figures.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;


@Entity
public class Circle extends Figure implements Serializable {
    @Id
    private Long id;


}
