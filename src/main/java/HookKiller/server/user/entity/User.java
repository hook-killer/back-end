package HookKiller.server.user.entity;

import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.user.type.Status;
import HookKiller.server.user.type.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Getter
@Builder
@AllArgsConstructor
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
    
//    @Builder
//    public User(String email, String password, String nickName, String role) {
//        this.email = email;
//        this.password = password;
//        this.nickName = nickName;
//        this.role = UserRole.valueOf(role);
//    }
    
    //    private Long certificateKeyId;

//    private OauthInfo oauthInfo;

//    private String expoToken;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;
//
//    @Enumerated(EnumType.STRING)
//    private LoginType loginType;
//
//    @Column
//    @ColumnDefault(value = "false")
//    private Boolean isDeleted;

}
