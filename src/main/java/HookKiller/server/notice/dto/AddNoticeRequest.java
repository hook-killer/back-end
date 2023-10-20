package HookKiller.server.notice.dto;

import HookKiller.server.common.type.LanguageType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 공지사항 추가 요청 DTO
 */
@Getter
@Setter
public class AddNoticeRequest {
    @NotNull(message = "언어 타입을 선택해야 합니다.")
    private LanguageType language;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String title;

    @NotEmpty(message = "제목을 입력해주세요.")
    private String content;

}
