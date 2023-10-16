package HookKiller.server.user.repository;

import HookKiller.server.user.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
}
