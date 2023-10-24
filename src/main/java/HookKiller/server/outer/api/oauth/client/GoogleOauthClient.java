package HookKiller.server.outer.api.oauth.client;

import HookKiller.server.outer.api.oauth.dto.response.GoogleTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
            name = "GoogleOauthClient",
            url = "https://oauth2.googleapis.com")
public interface GoogleOauthClient {
    @PostMapping(
            value = "/token?grant_type=authorization_code&client_id={CLIENT_ID}&redirect_uri={REDIRECT_URI}&code={CODE}&client_secret={CLIENT_SECRET}",
            produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    GoogleTokenResponse googleAuth(
            @PathVariable("CLIENT_ID") String clientId,
            @PathVariable("REDIRECT_URI") String redirectUri,
            @PathVariable("CODE") String code,
            @PathVariable("CLIENT_SECRET") String client_secret
    );

}
