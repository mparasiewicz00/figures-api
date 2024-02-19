package pl.kurs.figures.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.security.model.Role;
import pl.kurs.figures.security.model.User;
import pl.kurs.figures.security.repository.UserRepository;
import pl.kurs.figures.service.FigureFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@Sql(scripts={"classpath:initial.sql"})
class FigureControllerTest {

    private MockMvc postman;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private FigureRepository repository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FigureFactory factory;
    private User user;

    //    @Before
//    public void init() {
//        testFigure = new Square();
//        testFigure.setType("SQUARE");
//        testFigure.setId(1L);
//        testFigure.setCreatedBy("user");
//        repository.save(testFigure);
//    }
    @BeforeEach
    public void setup() {
        user = setupUser();
        this.postman = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }


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

    @Test
    void shouldAddSquare() throws Exception {
        List<Double> parameters = List.of(10.0);
        CreateFigureCommand command = new CreateFigureCommand("SQUARE", parameters);
        postman.perform(post("/api/v1/shapes")
                        .with(user(setupUser()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated());
        Figure savedFigure = repository.findById(1L).orElseThrow();
        assertEquals(savedFigure.getType(), command.getType());

    }

    @Test
    void shouldAddRectangle() throws Exception {
        List<Double> parameters = List.of(10.0, 20.0);
        CreateFigureCommand command = new CreateFigureCommand("RECTANGLE", parameters);
        postman.perform(post("/api/v1/shapes")
                        .with(user(setupUser()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated());
        Figure savedFigure = repository.findById(1L).orElseThrow();
        assertEquals(savedFigure.getType(), command.getType());

    }

    @Test
    void shouldFindFiguresCreatedByUser() throws Exception {
        this.postman.perform(get("/api/v1/shapes")
                        .with(user(user))
//                        .param("createdBy","user")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id").value(1));
    }
}