package src.server.jointx.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import src.server.jointx.entities.Theory;
import src.server.jointx.repos.TheoriesRepo;
import src.server.jointx.services.ImageService;

import java.util.List;

@RestController
@AllArgsConstructor
public class TheoryController {

    private TheoriesRepo theoriesRepo;
    private ImageService imageService;

    @GetMapping("/get/theory/image={id}")
    public byte[] getQuestionImage(@PathVariable long id){
        return imageService.getImage(id);
    }

    @GetMapping("/get/theories")
    public List<Theory> getAll() {
        return theoriesRepo.findAll();
    }

    @GetMapping("/get/theory={id}")
    public Theory getById(@PathVariable long id) {
        return theoriesRepo.findById(id).orElse(null);
    }

    @GetMapping("/get/theory/theme={themeId}")
    public Theory getByTheme(@PathVariable long themeId){
        return theoriesRepo.findByThemeIdEquals(themeId).orElse(null);
    }

    @DeleteMapping("/delete/theory={id}")
    public boolean deleteById(@PathVariable long id){
        try {
            theoriesRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
