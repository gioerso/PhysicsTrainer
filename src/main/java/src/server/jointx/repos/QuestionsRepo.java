package src.server.jointx.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import src.server.jointx.entities.Question;

import java.util.List;

public interface QuestionsRepo extends JpaRepository<Question, Long> {
    List<Question> findAllByBlockIdEquals(long blockId);
}
