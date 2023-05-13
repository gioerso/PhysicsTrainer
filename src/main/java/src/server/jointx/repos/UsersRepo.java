package src.server.jointx.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import src.server.jointx.entities.User;

import java.util.List;

public interface UsersRepo extends JpaRepository<User, Long> {
    List<User> findAllByScoreEquals(long score);
    List<User> findAllByScoreGreaterThanEqual(long score);
    List<User> findAllByScoreGreaterThan(long score);
    List<User> findAllByScoreLessThanEqual(long score);
    List<User> findAllByScoreLessThan(long score);
}
