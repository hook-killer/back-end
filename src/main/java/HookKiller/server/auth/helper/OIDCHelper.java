package HookKiller.server.auth.helper;

import HookKiller.server.common.dto.OIDCDto;
import HookKiller.server.jwt.JwtOIDCProvider;
import HookKiller.server.outer.api.oauth.dto.OIDCPublicKeyDto;
import HookKiller.server.outer.api.oauth.dto.OIDCResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OIDCHelper {

    private final JwtOIDCProvider jwtOIDCProvider;

    private String getKidFromUnsignedIdToken(String token, String iss, String aud) {
        log.info("getKidFromUnsignedIdToken 들어옴! token : {}", token);
        log.info("getKidFromUnsignedTokenHeader의 반환값 : {}", jwtOIDCProvider.getKidFromUnsignedTokenHeader(token, iss, aud));
        return jwtOIDCProvider.getKidFromUnsignedTokenHeader(token, iss, aud);
    }

    public OIDCDto getPayloadFromIdToken(
            String token, String iss, String aud, OIDCResponse oidcResponse) {
        log.info("getPayloadFromIdToken 들어옴! token : {}, iss : {}, aud : {}", token, iss, aud);
        String kid = getKidFromUnsignedIdToken(token, iss, aud);

        log.info("kid = {}", kid);

        OIDCPublicKeyDto oidcPublicKeyDto =
                oidcResponse.getKeys().stream()
                        .filter(o -> o.getKid().equals(kid))
                        .findFirst()
                        .orElseThrow();
        
        log.info("OIDCPublicKeyDto : {}", oidcPublicKeyDto.toString());

        return jwtOIDCProvider.getOIDCTokenBody(
                token, oidcPublicKeyDto.getN(), oidcPublicKeyDto.getE());
    }
}
