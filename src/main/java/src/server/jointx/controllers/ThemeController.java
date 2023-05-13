package src.server.jointx.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import src.server.jointx.entities.Theme;
import src.server.jointx.repos.ThemesRepo;

import java.util.List;

@RestController
@AllArgsConstructor
public class ThemeController {

    private ThemesRepo themesRepo;

    @GetMapping("/get/themes")
    public List<Theme> getAll(){
        return themesRepo.findAll();
    }

    @GetMapping("/get/theme={id}")
    public Theme getById(@PathVariable long id){
        return themesRepo.findById(id).orElse(null);
    }

    @DeleteMapping("/delete/theme={id}")
    public boolean deleteById(@PathVariable long id){
        try {
            themesRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
