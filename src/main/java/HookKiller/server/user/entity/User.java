package HookKiller.server.user.entity;

import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.user.type.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Getter
@Setter
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

    public void updatePasswordAndNickname(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }
    
    @Builder
    public User(String email, String password, String nickName, String role) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.role = UserRole.valueOf(role);
    }
    
    //    private Long certificateKeyId;

//    private OauthInfo oauthInfo;

//    private String expoToken;


//    @Enumerated(EnumType.STRING)
//    @ColumnDefault(value = "NOT_ACTIVE")
//    private Status status;
//
//    @Enumerated(EnumType.STRING)
//    private LoginType loginType;
//
//    @Column
//    @ColumnDefault(value = "false")
//    private Boolean isDeleted;

}
