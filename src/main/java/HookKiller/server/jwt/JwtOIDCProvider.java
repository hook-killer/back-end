package HookKiller.server.jwt;

import HookKiller.server.auth.exception.ExpiredTokenException;
import HookKiller.server.auth.exception.InvalidTokenException;
import HookKiller.server.common.dto.OIDCDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtOIDCProvider {

    private final String KID = "kid";

    // 외부에서 돌릴 검증 로직
    public String getKidFromUnsignedTokenHeader(String token, String iss, String aud) {
        return (String) getUnsignedTokenClaims(token, iss, aud).getHeader().get(KID);
    }

    private Jwt<Header, Claims> getUnsignedTokenClaims(String token, String iss, String aud) {
        try {
            Jwt<Header, Claims> headerClaimsJwt = Jwts.parserBuilder()
                    .requireAudience(aud)
                    .requireIssuer(iss)
                    .build()
                    .parseClaimsJwt(getParseUnsignedToken(token));
            return headerClaimsJwt;
        } catch (ExpiredJwtException e) {
            log.info("jwt 생성 유효시간 초과");
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            log.info("토큰 만료");
            e.printStackTrace();
            throw InvalidTokenException.EXCEPTION;
        }
    }

    // base 64 인코딩으로 .을 구분자로 인코딩 됐으므로 파싱
    private String getParseUnsignedToken(String token) {
        String[] splitToken = token.split("\\.");
        if (splitToken.length != 3) throw InvalidTokenException.EXCEPTION;
        return splitToken[0] + "." + splitToken[1] + ".";
    }

    public Jws<Claims> getOIDCTokenJws(String token, String modulus, String exponent) {
        try {
            // OIDC 공개키의 모듈(m)과 지수(e)로 jwt 토큰 파싱
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getRSAPublicKey(modulus, exponent))
                    .build()
                    .parseClaimsJws(token);
            return claimsJws;
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public OIDCDto getOIDCTokenBody(String token, String modulus, String exponent) {
        Claims body = getOIDCTokenJws(token, modulus, exponent).getBody();
        OIDCDto dto = new OIDCDto(
                body.getIssuer(),
                body.getAudience(),
                body.getSubject(),
                body.get("email", String.class),
                body.get("nickname", String.class),
                body.get("picture", String.class));
        return dto;
    }

    private Key getRSAPublicKey(String modulus, String exponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodeN = Base64.getUrlDecoder().decode(modulus);
        byte[] decodeE = Base64.getUrlDecoder().decode(exponent);
        BigInteger n = new BigInteger(1, decodeN);
        BigInteger e = new BigInteger(1, decodeE);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
        return keyFactory.generatePublic(keySpec);
    }
}
