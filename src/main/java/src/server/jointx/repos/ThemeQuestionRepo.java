package src.server.jointx.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import src.server.jointx.entities.ThemeQuestion;

public interface ThemeQuestionRepo extends JpaRepository<ThemeQuestion, Long> {
}
