package HookKiller.server.user.repository;

import HookKiller.server.user.entity.OauthInfo;
import HookKiller.server.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);

    Optional<User> findByOauthInfo(OauthInfo oauthInfo);
}
