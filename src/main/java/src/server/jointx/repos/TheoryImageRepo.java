package src.server.jointx.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import src.server.jointx.entities.TheoryImage;

public interface TheoryImageRepo extends JpaRepository<TheoryImage, Long> {
}
