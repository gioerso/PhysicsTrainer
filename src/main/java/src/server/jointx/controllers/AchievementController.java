package src.server.jointx.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import src.server.jointx.enums.Achievement;

import java.util.Arrays;
import java.util.List;

@RestController
public class AchievementController {

    @GetMapping(value = "/get/achievements")
    public List<Achievement> getAll(){
        return Arrays.stream(Achievement.values()).toList();
    }
}
