package HookKiller.server.user.repository;

import HookKiller.server.user.entity.OauthInfo;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.type.Status;
import HookKiller.server.user.type.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    
    Optional<User> findByNickName(String nickName);
    
    boolean existsByEmail(String email);

    Page<User> findAllByRoleAndStatusOrderByCreateAtDesc(UserRole role, Status status, Pageable pageable);

    Optional<User> findByOauthInfo(OauthInfo oauthInfo);

    Optional<User> findByVerificationToken(String verificationToken);
}
