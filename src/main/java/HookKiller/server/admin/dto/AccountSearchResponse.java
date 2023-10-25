package HookKiller.server.admin.dto;

import HookKiller.server.user.entity.User;
import HookKiller.server.user.type.LoginType;
import HookKiller.server.user.type.Status;
import HookKiller.server.user.type.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AccountSearchResponse {
    private Long id;
    private String email;
    private String nickName;
    private String thumbnail;
    private UserRole role;
    private Status status;
    private LoginType loginType;

    public static AccountSearchResponse of(User user ){
        return AccountSearchResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .thumbnail(user.getThumbNail())
                .nickName(user.getNickName())
                .role(user.getRole())
                .status(user.getStatus())
                .loginType(user.getLoginType())
                .build();
    }
}
