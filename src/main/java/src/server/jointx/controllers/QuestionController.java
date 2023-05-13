package src.server.jointx.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import src.server.jointx.entities.Question;
import src.server.jointx.repos.QuestionsRepo;
import src.server.jointx.services.ImageService;
import src.server.jointx.services.QuestionService;

import java.util.List;

@RestController
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionsRepo questionsRepo;
    private final ImageService imageService;

    @GetMapping("/get/question/image={id}")
    public byte[] getQuestionImage(@PathVariable long id){
        return imageService.getImage(id);
    }

    @GetMapping("/get/questions")
    public List<Question> getAll() {
        return questionsRepo.findAll();
    }

    @GetMapping("/get/question={id}")
    public Question getById(@PathVariable long id) {
        return questionsRepo.findById(id).orElse(null);
    }

    @GetMapping("/get/questions/block={blockId}")
    public List<Question> getByBlock(@PathVariable long blockId){
        return questionsRepo.findAllByBlockIdEquals(blockId);
    }

    @GetMapping("/get/questions/complexity={complexity}")
    public List<Question> getByComplexityEquals(@PathVariable int complexity){
        return questionService.findAllByAchievementComplexityEquals(complexity);
    }

    @GetMapping("/get/questions/complexity>={complexity}")
    public List<Question> getByComplexityGreaterEquals(@PathVariable int complexity){
        return questionService.findAllByAchievementComplexityGreaterEquals(complexity);
    }

    @GetMapping("/get/questions/complexity<={complexity}")
    public List<Question> getByComplexityLessEquals(@PathVariable int complexity){
        return questionService.findAllByAchievementComplexityLessEquals(complexity);
    }

    @GetMapping("/get/questions/complexity>{complexity}")
    public List<Question> getByComplexityGreater(@PathVariable int complexity){
        return questionService.findAllByAchievementComplexityEquals(complexity);
    }

    @GetMapping("/get/questions/complexity<{complexity}")
    public List<Question> getByComplexityLess(@PathVariable int complexity){
        return questionService.findAllByAchievementComplexityLess(complexity);
    }

    @DeleteMapping("/delete/question={id}")
    public boolean deleteById(@PathVariable long id){
        try {
            questionsRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
