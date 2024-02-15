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


import java.time.LocalDate;

@Getter
@Setter
public abstract class FigureDTO {

    private Long id;
    private String type;
    private Integer version;
    private String createdBy;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;
    private String lastModifiedBy;
    private double area;
    private double perimeter;

    public FigureDTO(String type) {
        this.type = type;
    }
}
