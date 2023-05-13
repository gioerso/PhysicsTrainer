package src.server.jointx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import src.server.jointx.config.SecurityConfig;
import src.server.jointx.controllers.QuestionController;
import src.server.jointx.entities.Question;
import src.server.jointx.entities.ThemeQuestion;
import src.server.jointx.enums.Achievement;
import src.server.jointx.repos.QuestionsRepo;
import src.server.jointx.services.ImageService;
import src.server.jointx.services.QuestionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(QuestionController.class)
@Import(QuestionController.class)
public class QuestionControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionsRepo questionsRepo;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private ImageService imageService;

    private final List<Question> questions = new ArrayList<>(Arrays.asList(
            new Question(1, null, "Вопрос 1", "Верно ли выражение:", null, Achievement.HARD, new ArrayList<>()),
            new Question(2, null, "Вопрос 2", "Верно ли выражение:", null, Achievement.EXAMPLE, new ArrayList<>()),
            new Question(3, null, "Вопрос 3", "Верно ли выражение:", null, Achievement.VERY_EASY, new ArrayList<>()),
            new Question(4, null, "Вопрос 4", "Верно ли выражение:", null, Achievement.MEDIUM, new ArrayList<>()),
            new Question(5, null, "Вопрос 5", "Верно ли выражение:", null, Achievement.EASY, new ArrayList<>())
            ));



    @BeforeEach
    private void setUp(){
        when(questionsRepo.findAll()).thenReturn(questions);
    }


    @Test
    public void findAllTest() {
        String expected = Arrays.toString(questionsRepo.findAll().stream().map(x
                -> "\"" + x + "\"").toArray(String[]::new)).replace(" ", "");
        try {
            mvc.perform(
                    MockMvcRequestBuilders.get("/get/questions").accept(MediaType.APPLICATION_JSON)
            ).andExpect(content().string(expected));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
