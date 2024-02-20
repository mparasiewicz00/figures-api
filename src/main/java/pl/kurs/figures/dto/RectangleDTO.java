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
}
