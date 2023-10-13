package HookKiller.server.user.dto;

import HookKiller.server.outer.api.oauth.dto.KakaoInfoResponse;
import HookKiller.server.user.entity.OauthInfo;
import HookKiller.server.user.entity.OauthProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoUserInfoDto {
  
  private final String oauthId;
  
  private final String email;
  private final KakaoInfoResponse.KakaoAccount.Profile profile;
  // oauth 제공자
  private final OauthProvider oauthProvider;
  
  public OauthInfo toOauthInfo() {
    return OauthInfo.builder().oid(oauthId).provider(oauthProvider).build();
  }
}
