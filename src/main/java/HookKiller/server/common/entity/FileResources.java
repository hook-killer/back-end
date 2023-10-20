package HookKiller.server.common.entity;

import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.common.file.NaverObjectStorageUsageType;
import HookKiller.server.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 업데이트는 없기 때문에 ㄱㅊ, 어느 사용자가 등록했는지 알기 위한 유틸일뿐
 */
@Entity
@Getter
@Table(name = "tbl_resources")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileResources extends AbstractTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String path;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NaverObjectStorageUsageType usageType;

    @ManyToOne
    @JoinColumn(name = "created_user")
    @NotNull
    private User createdUser;

    @Builder
    public FileResources(String path, NaverObjectStorageUsageType usageType, User createdUser) {
        this.path = path;
        this.usageType = usageType;
        this.createdUser = createdUser;
    }
}
