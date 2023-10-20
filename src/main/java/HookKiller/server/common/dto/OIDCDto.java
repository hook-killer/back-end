package HookKiller.server.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class OIDCDto {
    /**
     * issuer ex https://kauth.kakao.com
     */
    private String iss;
    /**
     * idToken이 발급된 앱의 앱 키. 인가 코드 받기 요청 시 client_id에 전달된 앱 키
     */
    private String aud;
    /**
     * idToken에 해당하는 사용자의 회원번호
     */
    private String sub;
    
    private String email;
    
    private String kakaoNickName;
    
    private String kakaoThumbnailImg;
}
