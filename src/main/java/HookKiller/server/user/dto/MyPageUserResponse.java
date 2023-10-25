package HookKiller.server.user.dto;

import HookKiller.server.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
@Builder
@AllArgsConstructor
public class MyPageUserResponse {
    private Long userId;
    private String email;
    private String thumbnail;
    private String nickName;
    private String createAt;

    public static MyPageUserResponse from(User user) {
        return MyPageUserResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .thumbnail(user.getThumbNail())
                .nickName(user.getNickName())
                .createAt(formatTimestamp(user.getCreateAt()))
                .build();
    }

    private static String formatTimestamp(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(timestamp);
    }
}
