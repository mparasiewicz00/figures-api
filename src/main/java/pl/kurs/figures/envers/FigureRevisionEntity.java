package pl.kurs.figures.envers;

import com.querydsl.core.annotations.QueryEntities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import pl.kurs.figures.security.model.User;

@Getter
@Setter
@Entity
@Table(name = "figure_envers_info")
@RevisionEntity(FigureRevisionEntityListener.class)
public class FigureRevisionEntity extends DefaultRevisionEntity {

    @Column(name = "user")
    private String username;
}