package pl.kurs.figures.controller;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionMetadata;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.figures.envers.FigureRevisionEntity;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.repository.FigureRevisionRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shapes-history")
@AllArgsConstructor
public class RevisionController {

    private final FigureRevisionRepository figureRevisionRepository;
    @GetMapping("/{id}/revision")
    public List<Figure> getFigureRevision(@PathVariable Long id) {
        return figureRevisionRepository
                .findRevisions(id)
                .stream()
                .map(Revision::getEntity)
                .toList();
    }

    @GetMapping("/{id}/creator")
    public String getCreatorUsername(@PathVariable Long id) {
        Revision<Integer, Figure> revision = figureRevisionRepository.findRevision(id, 1).orElseThrow();
        RevisionMetadata<Integer> metadata = revision.getMetadata();
        FigureRevisionEntity figureRevisionEntity = metadata.getDelegate();
        return figureRevisionEntity.getUsername();

    }

}
