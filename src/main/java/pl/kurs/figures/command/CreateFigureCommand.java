package pl.kurs.figures.command;


import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateFigureCommand {
    private String type;
    private double[] parameters;

}
