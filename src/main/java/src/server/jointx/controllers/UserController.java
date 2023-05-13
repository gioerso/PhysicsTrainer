package src.server.jointx.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import src.server.jointx.entities.Block;
import src.server.jointx.entities.User;
import src.server.jointx.entities.UserBlock;
import src.server.jointx.repos.BlocksRepo;
import src.server.jointx.repos.UserBlockRepo;
import src.server.jointx.repos.UsersRepo;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private final UsersRepo usersRepo;
    private final BlocksRepo blocksRepo;
    private final UserBlockRepo userBlockRepo;

    @GetMapping("/get/users")
    public List<User> getAll() {
        return usersRepo.findAll();
    }

    @GetMapping("/get/user={id}")
    public User getById(@PathVariable long id) {
        return usersRepo.findById(id).orElse(null);
    }

    @GetMapping("/get/users/score={score}")
    public List<User> getScoredEquals(@PathVariable long score) {
        return usersRepo.findAllByScoreEquals(score);
    }

    @GetMapping("/get/users/score>{score}")
    public List<User> getScoredBefore(@PathVariable long score) {
        return usersRepo.findAllByScoreLessThan(score);
    }

    @GetMapping("/get/users/score<{score}")
    public List<User> getScoredAfter(@PathVariable long score) {
        return usersRepo.findAllByScoreGreaterThan(score);
    }

    @GetMapping("/get/users/score>={score}")
    public List<User> getScoredBeforeOrEquals(@PathVariable long score) {
        return usersRepo.findAllByScoreLessThanEqual(score);
    }

    @GetMapping("/get/users/score<={score}")
    public List<User> getScoredAfterOrEquals(@PathVariable long score) {
        return usersRepo.findAllByScoreGreaterThanEqual(score);
    }

    @DeleteMapping("/delete/user={id}")
    public boolean deleteById(@PathVariable long id) {
        try {
            usersRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PutMapping("/update/user={id}/attrs={name}+{score}")
    public boolean updateById(@PathVariable long id,
                              @PathVariable String name,
                              @PathVariable long score) {
        Optional<User> optionalUser = usersRepo.findById(id);
        if (optionalUser.isEmpty()) return false;
        User presentUser = optionalUser.get();
        if (name.equals(presentUser.getName())
                && score == presentUser.getScore()) return true;
        try {
            presentUser.setName(name);
            presentUser.setScore(score);
            usersRepo.save(presentUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/post/user={userId}/completed={blockId}")
    public boolean completeBlock(@PathVariable long userId, @PathVariable long blockId){
        Optional<UserBlock> optionalUserBlock = userBlockRepo.findByUserIdEqualsAndBlockIdEquals(userId, blockId);
        if(optionalUserBlock.isPresent()) return true;
        Optional<User> optionalUser = usersRepo.findById(userId);
        Optional<Block> optionalBlock = blocksRepo.findById(blockId);
        if(optionalBlock.isEmpty() || optionalUser.isEmpty()) return false;
        UserBlock presentUserBlock = new UserBlock(optionalUser.get(), optionalBlock.get());
        userBlockRepo.save(presentUserBlock);
        return true;
    }
}
