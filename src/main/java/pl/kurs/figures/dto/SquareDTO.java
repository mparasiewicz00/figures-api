package pl.kurs.figures.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SquareDTO extends FigureDTO{
    private double side_length;

    public SquareDTO(String type, double side_length) {
        super(type);
        this.side_length = side_length;
    }
}
