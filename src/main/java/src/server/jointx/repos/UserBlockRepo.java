package src.server.jointx.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import src.server.jointx.entities.UserBlock;

import java.util.Optional;

public interface UserBlockRepo extends JpaRepository<UserBlock, Long> {
    Optional<UserBlock> findByUserIdEqualsAndBlockIdEquals(long userId, long blockId);
}
