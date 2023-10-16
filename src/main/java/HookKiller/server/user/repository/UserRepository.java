package HookKiller.server.user.repository;

import HookKiller.server.user.entity.OauthInfo;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.type.Status;
import HookKiller.server.user.type.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);

    List<User> findAllByRole(UserRole role);

    Optional<User> findByOauthInfo(OauthInfo oauthInfo);
}
