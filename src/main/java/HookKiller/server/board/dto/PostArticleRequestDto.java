package HookKiller.server.board.dto;

import HookKiller.server.common.type.LanguageType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostArticleRequestDto {
  @NotNull(message = "게시판 ID는 필수 입니다.")
  private Long boardId;

  private Long articleId;

  @NotNull(message = "원본 언어는 필수 선택 하셔야 합니다.")
  @Enumerated(EnumType.STRING)
  private LanguageType orgArticleLanguage;

  @NotEmpty(message = "제목이 입력되지 않았습니다.")
  private String title;
  private String newTitle;

  @NotEmpty(message = "내용이 입력되지 않았습니다.")
  private String content;
  private String newContent;
}
