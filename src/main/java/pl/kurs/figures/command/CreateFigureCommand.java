package pl.kurs.figures.command;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@NotBlank
@NotNull
public class CreateFigureCommand {
    private String type;
    private double[] parameters;

}
