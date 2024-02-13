package pl.kurs.figures.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import pl.kurs.figures.serializer.FigureDTOSerializer;

import java.time.LocalDate;

@Getter
@Setter
@JsonSerialize(using = FigureDTOSerializer.class)
public class FigureDTO {
    private String type;
    private Long id;
    private Integer version;
    private String createdBy;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;
    private String lastModifiedBy;
    private double area;
    private double perimeter;
    private double radius;
    private double first_side_length;
    private double second_side_length;
    private double side_length;


}
