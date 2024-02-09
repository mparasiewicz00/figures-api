package pl.kurs.figures.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FigureDTO {
    private Long id;
    private String type;
    private double[] parameters;
    private double area;
    private double perimeter;

}
