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
public class ModifyFigureCommand {
    private Long id;
    private List<Double> parameters;
}
