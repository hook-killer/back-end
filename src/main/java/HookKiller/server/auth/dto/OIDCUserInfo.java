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

  private String picture;

  private String name;

  @Builder
  public OIDCUserInfo(OauthInfo oauthInfo, String email, String nickName, String thumbnailImg, String picture, String name) {
    this.oauthInfo = oauthInfo;
    this.email = email;
    this.nickName = nickName;
    this.thumbnailImg = thumbnailImg;
    this.picture = picture;
    this.name = name;
  }
}
