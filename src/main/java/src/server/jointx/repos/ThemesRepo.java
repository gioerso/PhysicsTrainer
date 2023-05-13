package src.server.jointx.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import src.server.jointx.entities.Theme;

public interface ThemesRepo extends JpaRepository<Theme, Long> {
}
