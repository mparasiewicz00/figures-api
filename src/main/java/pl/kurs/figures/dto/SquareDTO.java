package pl.kurs.figures.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SquareDTO extends FigureDTO{
    private double side_length;

    public SquareDTO(String type) {
        super(type);
    }
}
