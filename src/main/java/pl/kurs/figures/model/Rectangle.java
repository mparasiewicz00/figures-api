package pl.kurs.figures.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
public class Rectangle implements Serializable {
    @Id
    private Long id;

}
