package pl.kurs.figures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.figures.model.Figure;

public interface FigureRepository extends JpaRepository<Figure, Long> {
}
