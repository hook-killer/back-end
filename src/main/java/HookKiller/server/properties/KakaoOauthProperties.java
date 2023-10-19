package HookKiller.server.properties;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@ConfigurationProperties(prefix = "oauth2.kakao")
public class KakaoOauthProperties {
    
    private String baseUrl;
    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String appId;
    private String adminKey;

    // base url
    public String getKakaoBaseUrl() {
        return this.baseUrl;
    }

    // rest api key
    public String getKakaoClientId() {
        return this.clientId;
    }

    // redirect url
    public String getKakaoRedirectUrl() {
        return this.redirectUrl;
    }

    // secret key
    public String getKakaoClientSecret() {
        return this.clientSecret;
    }

    // native app key
    public String getKakaoAppId() {
        return this.appId;
    }

    public String getKakaoAdminKey() {
        return this.adminKey;
    }
}
