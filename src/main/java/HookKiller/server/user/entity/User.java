package HookKiller.server.user.entity;

import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.common.util.SecurityUtils;
import HookKiller.server.user.type.LoginType;
import HookKiller.server.user.type.Status;
import HookKiller.server.user.type.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@Table(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AbstractTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String nickName;

    private String thumbNail;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private OauthInfo oauthInfo;

    private String verificationToken;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_ACTIVE;

    @Builder
    public User(
                String email,
                String password,
                String nickName,
                String thumbNail,
                UserRole role,
                OauthInfo oauthInfo,
                LoginType loginType,
                Status status
    ) {
        this.email = email;
        this.password = SecurityUtils.passwordEncoder.encode(password);
        this.nickName = nickName;
        this.thumbNail = thumbNail;
        this.role = role;
        this.status = status;
        this.oauthInfo = oauthInfo;
        this.loginType = loginType;
    }


    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    public void updateUserStatus(Status userStatus) {
        this.status = userStatus;
    }

    public void setPassword(String password) {
        this.password = SecurityUtils.passwordEncoder.encode(password);
    }
}
