package HookKiller.server.user.repository;

import HookKiller.server.user.entity.User;
import HookKiller.server.user.type.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNickName(String nickname);
    
    Optional<User> findByEmailAndPassword(String email, String password);
    
    boolean existsByEmail(String email);

    Optional<User> findAllByRole(UserRole role);
}
