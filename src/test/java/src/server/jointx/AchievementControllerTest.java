package src.server.jointx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import src.server.jointx.config.SecurityConfig;
import src.server.jointx.controllers.AchievementController;
import src.server.jointx.enums.Achievement;
import java.util.Arrays;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(AchievementController.class)
@Import(AchievementController.class)
public class AchievementControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mvc;

    /** ["EXAMPLE","VERY_EASY","EASY","MEDIUM","HARD","VERY_HARD"] **/
    @Test
    public void findAllTest() {
        String expected = Arrays.toString(Arrays.stream(Achievement.values()).map(x
                -> "\"" + x + "\"").toArray(String[]::new)).replace(" ", "");
        try {
          mvc.perform(
                    MockMvcRequestBuilders.get("/get/achievements").accept(MediaType.APPLICATION_JSON)
            ).andExpect(content().string(expected));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
