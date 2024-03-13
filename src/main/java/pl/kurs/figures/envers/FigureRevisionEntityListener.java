package pl.kurs.figures.envers;

import org.hibernate.envers.RevisionListener;
import pl.kurs.figures.security.service.UserService;
import pl.kurs.figures.security.service.UserServiceImpl;

public class FigureRevisionEntityListener implements RevisionListener {

    UserService userService;
    @Override
    public void newRevision(Object revisionEntity) {
        FigureRevisionEntity customRevisionEntity = (FigureRevisionEntity) revisionEntity;
        // TODO ("Not yet implemented")

        customRevisionEntity.setUsername(userService.userDetailsService().toString());
    }
}