package pl.kurs.figures.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import pl.kurs.figures.command.FigureSearchCriteria;
import pl.kurs.figures.model.QFigureView;

public class FigureViewQueryCreator {

    public static Predicate createPredicate(FigureSearchCriteria criteria) {
        QFigureView qFigureView = QFigureView.figureView;
        BooleanBuilder builder = new BooleanBuilder();

        if (criteria.getType() != null) {
            builder.and(qFigureView.type.eq(criteria.getType()));
        }
        if (criteria.getCreatedBy() != null) {
            builder.and(qFigureView.createdBy.eq(criteria.getCreatedBy()));
        }
        if (criteria.getAreaFrom() != null && criteria.getAreaTo() != null) {
            builder.and(qFigureView.area.between(criteria.getAreaFrom(), criteria.getAreaTo()));
        }
        if (criteria.getPerimeterFrom() != null && criteria.getPerimeterTo() != null) {
            builder.and(qFigureView.perimeter.between(criteria.getPerimeterFrom(), criteria.getPerimeterTo()));
        }

        if (criteria.getSideLengthFrom() != null && criteria.getSideLengthTo() != null) {
            builder.and(qFigureView.sideLength.between(criteria.getSideLengthFrom(), criteria.getSideLengthTo()));
        }
        if (criteria.getRadiusFrom() != null && criteria.getRadiusTo() != null) {
            builder.and(qFigureView.radius.between(criteria.getRadiusFrom(), criteria.getRadiusTo()));
        }
        if (criteria.getFirstSideLengthFrom() != null && criteria.getFirstSideLengthTo() != null) {
            builder.and(qFigureView.firstSideLength.between(criteria.getFirstSideLengthFrom(), criteria.getFirstSideLengthTo()));
        }
        if (criteria.getSecondSideLengthFrom() != null && criteria.getSecondSideLengthTo() != null) {
            builder.and(qFigureView.secondSideLength.between(criteria.getSecondSideLengthFrom(), criteria.getSecondSideLengthTo()));
        }

        return builder;
    }
}

