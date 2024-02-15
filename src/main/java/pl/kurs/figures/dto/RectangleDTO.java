package pl.kurs.figures.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RectangleDTO extends FigureDTO{
    private double first_side_length;
    private double second_side_length;

    public RectangleDTO(String type) {
        super(type);
    }
}
