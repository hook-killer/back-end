package HookKiller.server.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AccessTokenDetail {

    private final Long userId;
    private final String role;
}
