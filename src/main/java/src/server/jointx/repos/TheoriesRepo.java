package src.server.jointx.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import src.server.jointx.entities.Theory;

import java.util.Optional;

public interface TheoriesRepo extends JpaRepository<Theory, Long> {
    Optional<Theory> findByThemeIdEquals(long themeId);
}
