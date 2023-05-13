package src.server.jointx.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import src.server.jointx.entities.Image;

import java.util.List;

public interface ImageRepo extends JpaRepository<Image, Long> {
   List<Image> findAllByTheoreticalIs(boolean theoretical);
}
