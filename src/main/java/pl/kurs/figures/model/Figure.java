package pl.kurs.figures.model;

import jakarta.persistence.*;

import java.io.Serializable;



@Entity
public class Figure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    String type;


}
