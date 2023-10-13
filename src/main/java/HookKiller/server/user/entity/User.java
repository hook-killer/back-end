package HookKiller.server.user.entity;

import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.user.type.LoginType;
import HookKiller.server.user.type.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
public class User extends AbstractTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String nickName;

    private String thumbnail;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    
    @Builder
    public User(String email, String password, String nickName, String role, LoginType loginType, OauthInfo oauthInfo) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.role = UserRole.valueOf(role);
        this.loginType = loginType;
        this.oauthInfo = oauthInfo;
    }

    @Builder
    public User(String email, String password, String nickName, String role, LoginType loginType) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.role = UserRole.valueOf(role);
        this.loginType = loginType;
    }
    
    //    private Long certificateKeyId;

    private OauthInfo oauthInfo;

//    private String expoToken;


//    @Enumerated(EnumType.STRING)
//    @ColumnDefault(value = "NOT_ACTIVE")
//    private Status status;
//
    @Enumerated(EnumType.STRING)
    private LoginType loginType;
//
//    @Column
//    @ColumnDefault(value = "false")
//    private Boolean isDeleted;

}
