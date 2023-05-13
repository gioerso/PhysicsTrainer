package src.server.jointx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
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
import src.server.jointx.controllers.UserController;
import src.server.jointx.repos.BlocksRepo;
import src.server.jointx.repos.UserBlockRepo;
import src.server.jointx.repos.UsersRepo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(UserController.class)
@Import(UserController.class)
public class UserControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersRepo usersRepo;

    @MockBean
    private BlocksRepo blocksRepo;

    @MockBean
    private UserBlockRepo userBlockRepo;

    @Test
    public void findAllTest() {
        String expected = "[]";
        try {
            mvc.perform(
                    MockMvcRequestBuilders.get("/get/users").accept(MediaType.APPLICATION_JSON)
            ).andExpect(content().string(expected));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
