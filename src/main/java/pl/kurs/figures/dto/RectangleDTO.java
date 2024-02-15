package pl.kurs.figures.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RectangleDTO extends FigureDTO{
    private double first_side_length;
    private double second_side_length;

    public RectangleDTO(String type, double first_side_length, double second_side_length) {
        super(type);
        this.first_side_length = first_side_length;
        this.second_side_length = second_side_length;
    }
}
