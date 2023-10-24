package HookKiller.server.properties;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@ConfigurationProperties(prefix = "oauth2.google")
public class GoogleOauthProperties {

    private String baseUrl;
    private String clientId;
    private String clientSecret;
    private String redirectUri;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }
}
