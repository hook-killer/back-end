package HookKiller.server.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Boolean 타입으로 Retrun이 필요할 때 Message도 보내야 하는 경우 공통으로 사용하면 좋을 ResponseDto
 */
@Getter
@Builder
@AllArgsConstructor
public class CommonBooleanResultResponse {
    private boolean result;
    private String message;
}
