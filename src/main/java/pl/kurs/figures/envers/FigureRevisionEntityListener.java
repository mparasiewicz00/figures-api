package pl.kurs.figures.envers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.RevisionListener;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import pl.kurs.figures.security.repository.UserRepository;
import pl.kurs.figures.security.service.UserService;
import pl.kurs.figures.security.service.UserServiceImpl;

public class FigureRevisionEntityListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        FigureRevisionEntity customRevisionEntity = (FigureRevisionEntity) revisionEntity;

        customRevisionEntity.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}