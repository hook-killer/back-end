package HookKiller.server.user.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    NOT_ACTIVE("NOT_ACTIVE"),
    ACTIVE("ACTIVE"),
    SUSPENSION("SUSPENSION"),
    DELETE("DELETE");

    private String value;
}
