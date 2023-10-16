package HookKiller.server.auth.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OauthRegisterRequest {

    @NotEmpty
    private String email;

    private String profileImage;

    @NotEmpty
    private String name;

}
