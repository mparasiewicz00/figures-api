package pl.kurs.figures.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CircleDTO extends FigureDTO {
    private double radius;
    public CircleDTO(String type, double radius) {
        super(type);
        this.radius = radius;
    }
}
