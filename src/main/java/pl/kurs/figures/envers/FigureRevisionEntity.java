package pl.kurs.figures.envers;

import com.querydsl.core.annotations.QueryEntities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import pl.kurs.figures.security.model.User;

@Getter
@Setter
@Entity
@Table(name = "figure_envers_info")
@RevisionEntity(FigureRevisionEntityListener.class)
public class FigureRevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private int id;
    @RevisionTimestamp
    private long timestamp;
    @Column(name = "user")
    private String username;

}