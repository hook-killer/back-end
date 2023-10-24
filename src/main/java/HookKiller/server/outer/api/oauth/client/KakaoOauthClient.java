package HookKiller.server.outer.api.oauth.client;

import HookKiller.server.outer.api.oauth.config.KakaoAuthFeignConfig;
import HookKiller.server.outer.api.oauth.dto.response.KakaoTokenResponse;
import HookKiller.server.outer.api.oauth.dto.response.OIDCResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "KakaoOauthClient",
        url = "https://kauth.kakao.com",
        configuration = KakaoAuthFeignConfig.class)
public interface KakaoOauthClient {

    @PostMapping("/oauth/token?grant_type=authorization_code&client_id={CLIENT_ID}&redirect_uri={REDIRECT_URI}&code={CODE}&client_secret={CLIENT_SECRET}")
    KakaoTokenResponse kakaoAuth(
            @PathVariable("CLIENT_ID") String clientId,
            @PathVariable("REDIRECT_URI") String redirectUri,
            @PathVariable("CODE") String code,
            @PathVariable("CLIENT_SECRET") String client_secret
    );

//    @Cacheable(cacheNames = "KakaoOIDC", cacheManager = "oidcCacheManager")
    @GetMapping("/.well-known/jwks.json")
    OIDCResponse getKakaoOIDCOpenKeys();
}
