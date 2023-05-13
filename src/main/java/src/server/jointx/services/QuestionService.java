package src.server.jointx.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import src.server.jointx.entities.Question;
import src.server.jointx.repos.QuestionsRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionsRepo questionsRepo;

    public List<Question> findAllByAchievementComplexityEquals(int complexity) {
        return questionsRepo.findAll().stream().filter(x
                -> x.getAchievement().getComplexity() == complexity).collect(Collectors.toList());
    }

    public List<Question> findAllByAchievementComplexityLess(int complexity) {
        return questionsRepo.findAll().stream().filter(x
                -> x.getAchievement().getComplexity() < complexity).collect(Collectors.toList());
    }

    public List<Question> findAllByAchievementComplexityGreaterEquals(int complexity) {
        return questionsRepo.findAll().stream().filter(x
                -> x.getAchievement().getComplexity() >= complexity).collect(Collectors.toList());
    }

    public List<Question> findAllByAchievementComplexityLessEquals(int complexity) {
        return questionsRepo.findAll().stream().filter(x
                -> x.getAchievement().getComplexity() <= complexity).collect(Collectors.toList());
    }

    public List<Question> findAllByAchievementComplexityGreater(int complexity) {
        return questionsRepo.findAll().stream().filter(x
                -> x.getAchievement().getComplexity() > complexity).collect(Collectors.toList());
    }

    public List<Question> findAllByAchievementScoreEquals(int score) {
        return questionsRepo.findAll().stream().filter(x
                -> x.getAchievement().getScoreReward() == score).collect(Collectors.toList());
    }
}
