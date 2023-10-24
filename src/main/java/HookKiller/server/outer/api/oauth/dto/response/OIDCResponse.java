package HookKiller.server.outer.api.oauth.dto.response;

import HookKiller.server.outer.api.oauth.dto.OIDCPublicKeyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class OIDCResponse {

    List<OIDCPublicKeyDto> keys;
}

