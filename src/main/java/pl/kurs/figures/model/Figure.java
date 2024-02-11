package pl.kurs.figures.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EqualsAndHashCode
@ToString
@Inheritance(strategy=SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "type")
@EntityListeners(AuditingEntityListener.class)
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
    @Column(name = "version")
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

}
