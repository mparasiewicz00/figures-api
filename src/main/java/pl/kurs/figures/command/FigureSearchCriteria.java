package pl.kurs.figures.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FigureSearchCriteria {
    private String createdBy;

    private String type;

    private Double areaFrom;
    private Double areaTo;

    private Double perimeterFrom;
    private Double perimeterTo;

    private LocalDate createdAtFrom;
    private LocalDate createdAtTo;

    private Double sideLengthFrom;
    private Double sideLengthTo;

    private Double radiusFrom;
    private Double radiusTo;

    private Double firstSideLengthFrom;
    private Double firstSideLengthTo;
    private Double secondSideLengthFrom;
    private Double secondSideLengthTo;


}
