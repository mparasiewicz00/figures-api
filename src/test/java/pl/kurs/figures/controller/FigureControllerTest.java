package pl.kurs.figures.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.kurs.figures.FiguresApiApplication;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.service.FigureService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FiguresApiApplication.class)
@AutoConfigureMockMvc
class FigureControllerTest {

    @Autowired
    private MockMvc postman;

    @Autowired
    private FigureRepository repository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldAddCircle() throws Exception {
        List<Double> parameters = List.of(5.0);
        CreateFigureCommand command = new CreateFigureCommand("CIRCLE", parameters);
        postman.perform(post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated());
        Figure savedFigure = repository.findById(1L).orElseThrow();
        assertEquals(savedFigure.getType(), command.getType());

    }
}