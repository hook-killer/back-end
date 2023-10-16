package HookKiller.server.auth.dto;

import HookKiller.server.user.entity.OauthInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OIDCUserInfo {
  
  private OauthInfo oauthInfo;
  
  private String email;
  
  private String nickName;
  
  private String thumbnailImg;
  
  @Builder
  public OIDCUserInfo(OauthInfo oauthInfo, String email, String nickName, String thumbnailImg) {
    this.oauthInfo = oauthInfo;
    this.email = email;
    this.nickName = nickName;
    this.thumbnailImg = thumbnailImg;
  }
}
