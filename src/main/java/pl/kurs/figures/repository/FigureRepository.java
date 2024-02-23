package pl.kurs.figures.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.security.model.User;

public interface FigureRepository extends JpaRepository<Figure, Long> {
    Page<Figure> findByUser(User user, Pageable pageable);
}
