package pl.kurs.figures.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Immutable
@Table(name = "figure_view")
@NoArgsConstructor
public class FigureView {

    @Id
    private Long id;
    private String type;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private String lastModifiedBy;
    private Double area;
    private Double perimeter;
    private Double radius;
    private Double firstSideLength;
    private Double secondSideLength;
    private Double sideLength;

}
