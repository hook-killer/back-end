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

    //    @NotNull
    @Column(unique = true)
    private String email;

    //    @NotNull
    private String password;

    //    @NotNull
    private String nickName;

    private String thumbnail;

    @Enumerated(EnumType.STRING)
    private UserRole role;

//    @Builder
//    public User(String email, String password, String nickName, String role, String thumbnail, LoginType loginType, OauthInfo oauthInfo) {
//        this.email = email;
//        this.password = password;
//        this.nickName = nickName;
//        this.role = UserRole.valueOf(role);
//        this.thumbnail = thumbnail;
//        this.loginType = loginType;
//        this.oauthInfo = oauthInfo;
//    }
//
//    @Builder
//    public User(String email, String password, String nickName, String role, LoginType loginType) {
//        this.email = email;
//        this.password = password;
//        this.nickName = nickName;
//        this.role = UserRole.valueOf(role);
//        this.loginType = loginType;
//    }
//
//    @Builder
//    public User(String role, OauthInfo oauthInfo) {
//        this.role = UserRole.valueOf(role);
//        this.oauthInfo = oauthInfo;
//    }

    //    private Long certificateKeyId;

    private OauthInfo oauthInfo;

    private String verificationToken;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_ACTIVE;


    @Builder
    public User(
                String email,
                String password,
                String nickName,
                String thumbnail,
                UserRole role,
                OauthInfo oauthInfo,
                LoginType loginType,
                Status status
    ) {
        this.email = email;
        this.password = SecurityUtils.passwordEncoder.encode(password);
        this.nickName = nickName;
        this.thumbnail = thumbnail;
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

    public void activeStatus() {
        this.status = Status.ACTIVE;
    }
}
