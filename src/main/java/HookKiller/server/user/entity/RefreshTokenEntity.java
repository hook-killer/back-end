package HookKiller.server.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Indexed;

@Getter
@Entity
@Table(name = "tbl_refresh_token")
@NoArgsConstructor
public class RefreshTokenEntity {

    @Id
    private Long id;

    private String refreshToken;

    private Long ttl;

    @Builder
    public RefreshTokenEntity(Long id, String refreshToken, Long ttl) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.ttl = ttl;
    }

    public void updateTTL(Long ttl) {
        this.ttl += ttl;
    }
}
