package HookKiller.server.auth.dto.response;

import HookKiller.server.outer.api.oauth.dto.response.GoogleTokenResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthTokenResponse {

    private String accessToken;
    private String refreshToken;
    private String idToken;
    private String expiresIn;
    private String scope;
    private String tokenType;

    public static OauthTokenResponse from(GoogleTokenResponse googleTokenResponse) {
        return OauthTokenResponse.builder()
                .accessToken(googleTokenResponse.getAccessToken())
                .expiresIn(googleTokenResponse.getExpiresIn())
                .scope(googleTokenResponse.getScope())
                .tokenType(googleTokenResponse.getTokenType())
                .build();
    }

}
