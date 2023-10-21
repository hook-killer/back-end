package HookKiller.server.board.dto;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class PostReplyRequestDto {
  
  private Long articleId;
  
  private LanguageType orgReplyLanguage;
  
  private String content;
  
}
