package pl.kurs.figures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import pl.kurs.figures.view.FigureView;

public interface FigureViewRepository extends JpaRepository<FigureView, Long>, QuerydslPredicateExecutor<FigureView> {

}
