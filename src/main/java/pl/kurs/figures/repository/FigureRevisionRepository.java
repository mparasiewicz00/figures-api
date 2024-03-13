package pl.kurs.figures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import pl.kurs.figures.model.Figure;

public interface FigureRevisionRepository extends JpaRepository<Figure, Long>, RevisionRepository<Figure, Long, Integer> {
}