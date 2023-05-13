package src.server.jointx.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import src.server.jointx.entities.Block;
import src.server.jointx.repos.BlocksRepo;

import java.util.List;

@RestController
@AllArgsConstructor
public class BlockController {

   private BlocksRepo blocksRepo;

    @GetMapping("/get/blocks")
    public List<Block> getAll(){
        return blocksRepo.findAll();
    }

    @GetMapping("/get/block={id}")
    public Block getById(@PathVariable long id){
        return blocksRepo.findById(id).orElse(null);
    }

    @DeleteMapping("/delete/block={id}")
    public boolean deleteById(@PathVariable long id){
        try {
            blocksRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
