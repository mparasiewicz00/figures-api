package pl.kurs.figures.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SquareDTO extends FigureDTO{
    private double side_length;
}
