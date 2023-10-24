package HookKiller.server.auth.helper;

import HookKiller.server.common.dto.OIDCDto;
import HookKiller.server.jwt.JwtOIDCProvider;
import HookKiller.server.outer.api.oauth.dto.OIDCPublicKeyDto;
import HookKiller.server.outer.api.oauth.dto.response.OIDCResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OIDCHelper {

    private final JwtOIDCProvider jwtOIDCProvider;

    private String getKidFromUnsignedIdToken(String token, String iss, String aud) {
        return jwtOIDCProvider.getKidFromUnsignedTokenHeader(token, iss, aud);
    }

    public OIDCDto getPayloadFromIdToken(
            String token, String iss, String aud, OIDCResponse oidcResponse) {
        String kid = getKidFromUnsignedIdToken(token, iss, aud);

        OIDCPublicKeyDto oidcPublicKeyDto =
                oidcResponse.getKeys().stream()
                        .filter(o -> o.getKid().equals(kid))
                        .findFirst()
                        .orElseThrow();

        return jwtOIDCProvider.getOIDCTokenBody(
                token, oidcPublicKeyDto.getN(), oidcPublicKeyDto.getE());
    }
}
