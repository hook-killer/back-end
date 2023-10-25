package HookKiller.server.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OIDCDto {
    /**
     * issuer
     * kakao : https://kauth.kakao.com
     * google: https://accounts.google.com
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
    
    private String nickName;
    
    private String thumbnailImg;

}
