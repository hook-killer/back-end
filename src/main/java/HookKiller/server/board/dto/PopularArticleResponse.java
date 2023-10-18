package HookKiller.server.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PopularArticleResponse {
    private Long articleId;
    private String title;

}
