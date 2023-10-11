package HookKiller.server.outer.api.oauth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OIDCResponse {

    List<OIDCPublicKeyDto> keys;
}

