package HookKiller.server.admin.dto;

import HookKiller.server.user.type.Status;
import lombok.Getter;

@Getter
public class AccountStatusRequestDto {
    private Long userId;
    private Status accountStatus;
}
