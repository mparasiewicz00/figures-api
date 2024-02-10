package pl.kurs.figures.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy=SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "type")
public abstract class Figure implements FigureInterface, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", updatable = false, insertable = false)
    private String type;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @CreatedBy
    @Column(name = "createdBy")
    private String createdBy;

    @CreatedDate
    @Column(name = "createdAt")
    private LocalDate createdAt;

    @LastModifiedDate
    @Column(name = "lastModifiedAt")
    private LocalDate lastModifiedAt;

    @LastModifiedBy
    @Column(name = "lastModifiedBy")
    private String lastModifiedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Double.compare(version, figure.version) == 0 && Objects.equals(id, figure.id) && Objects.equals(type, figure.type) && Objects.equals(createdBy, figure.createdBy) && Objects.equals(createdAt, figure.createdAt) && Objects.equals(lastModifiedAt, figure.lastModifiedAt) && Objects.equals(lastModifiedBy, figure.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, version, createdBy, createdAt, lastModifiedAt, lastModifiedBy);
    }

    @Override
    public String toString() {
        return "Figure{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", version=" + version +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedAt=" + lastModifiedAt +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                '}';
    }
}
