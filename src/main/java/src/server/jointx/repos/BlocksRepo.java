package src.server.jointx.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import src.server.jointx.entities.Block;

public interface BlocksRepo extends JpaRepository<Block, Long> {
}
