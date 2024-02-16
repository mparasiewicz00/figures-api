package pl.kurs.figures.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.kurs.figures.FiguresApiApplication;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.security.model.Role;
import pl.kurs.figures.security.model.User;
import pl.kurs.figures.security.repository.UserRepository;
import pl.kurs.figures.service.FigureService;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FiguresApiApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class FigureControllerTest {

    @Autowired
    private MockMvc postman;
    @Autowired
    private FigureRepository repository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    public User setupUser() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Role userRole = Role.USER;
        User testUser = new User("user", bCryptPasswordEncoder.encode("user"), userRole);
        return userRepository.save(testUser);
    }

    @Test
    void shouldAddCircle() throws Exception {
        List<Double> parameters = List.of(5.0);
        CreateFigureCommand command = new CreateFigureCommand("CIRCLE", parameters);
        postman.perform(post("/api/v1/shapes")
                        .with(user(setupUser()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated());
        Figure savedFigure = repository.findById(1L).orElseThrow();
        assertEquals(savedFigure.getType(), command.getType());

    }
}