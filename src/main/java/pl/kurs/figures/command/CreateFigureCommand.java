package pl.kurs.figures.command;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@NotBlank
@NotNull
@AllArgsConstructor
public class CreateFigureCommand {
    private String type;
    private List<Double> parameters;

}
