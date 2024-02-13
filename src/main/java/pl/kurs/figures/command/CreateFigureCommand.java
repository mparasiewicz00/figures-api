package pl.kurs.figures.command;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NotBlank
@NotNull
@AllArgsConstructor
public class CreateFigureCommand {
    @NotBlank
    private String type;
    @NotNull
    private List<Double> parameters;

}
