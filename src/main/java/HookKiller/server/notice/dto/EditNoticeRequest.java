package HookKiller.server.notice.dto;

import HookKiller.server.common.type.LanguageType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 공지사항 수정 요청 DTO
 */
@Getter
@Setter
public class EditNoticeRequest {
    @NotNull(message = "공지사항 게시물 ID가 입력되지 않았습니다.")
    private Long noticeArticleId;

    @NotNull(message = "언어 타입을 선택해야 합니다.")
    private LanguageType language;

    private String orgTitle;
    private String newTitle;

    private String orgContent;
    private String newContent;
}
