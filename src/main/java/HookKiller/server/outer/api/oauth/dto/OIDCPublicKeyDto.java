package HookKiller.server.outer.api.oauth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class OIDCPublicKeyDto {
    
    /**
     * @field
     * kid : 공캐키 ID
     * alg : 암호화 알고리즘
     * use : 공개키의 용도, sig(서명)으로 고정
     * n : 공개키의 모듈. 공개키는 모듈(n)과 지수(e)로 구성됨
     * e : 공개키의 지수. 공개키는 모듈(n)과 지수(e)로 구성됨
     */

    private String kty;
    private String kid;
    private String alg;
    private String use;
    private String n;
    private String e;
}
