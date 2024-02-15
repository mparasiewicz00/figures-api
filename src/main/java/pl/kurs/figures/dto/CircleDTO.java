package pl.kurs.figures.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CircleDTO extends FigureDTO {
    private double radius;

    public CircleDTO(String type) {
        super(type);
    }
}
