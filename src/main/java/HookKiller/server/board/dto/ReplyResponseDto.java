package HookKiller.server.board.dto;


import HookKiller.server.board.entity.Reply;
import HookKiller.server.board.entity.ReplyContent;
import HookKiller.server.board.type.ReplyStatus;
import HookKiller.server.common.AbstractTimeStamp;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResponseDto extends AbstractTimeStamp {

  private Long articleId;

  private Long replyId;

  private LanguageType orgReplyLanguage;

  private User createUser;

  private String content;
  
  private ReplyStatus replyStatus;

  public static ReplyResponseDto of(Reply reply, ReplyContent replyContent) {
    ReplyResponseDto result = ReplyResponseDto.builder()
            .replyId(reply.getId())
            .articleId(reply.getArticle().getId())
            .createUser(reply.getCreatedUser())
            .content(replyContent.getContent())
            .orgReplyLanguage(reply.getOrgReplyLanguage())
            .replyStatus(reply.getReplyStatus())
            .build();
    result.setCreateAt(reply.getCreateAt());
    result.setUpdateAt(reply.getUpdateAt());
    return result;
  }

}
