package HookKiller.server.outer.api.oauth.client;

import HookKiller.server.outer.api.oauth.dto.response.GoogleInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "GoogleTokenInfo",
        url = "https://www.googleapis.com"
)
public interface GoogleTokenInfo {

    @GetMapping("/oauth2/v1/userinfo?alt=json&access_token={ACCESS_TOKEN}")
    GoogleInfoResponse googleUserInfo(@PathVariable("ACCESS_TOKEN") String accessToken);
}
