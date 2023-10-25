package HookKiller.server.jwt;

import HookKiller.server.auth.exception.ExpiredTokenException;
import HookKiller.server.auth.exception.InvalidTokenException;
import HookKiller.server.common.dto.AccessTokenDetail;
import HookKiller.server.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static HookKiller.server.jwt.ClaimVal.ACCESS_TOKEN;
import static HookKiller.server.jwt.ClaimVal.REFRESH_TOKEN;
import static HookKiller.server.jwt.ClaimVal.TOKEN_ROLE;
import static HookKiller.server.jwt.ClaimVal.TYPE;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    // 시크릿키 가져와서 저장
    private Key getSecertKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    // 모든 token에 대한 사용자 속성정보 조회
    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSecertKey()).build().parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }
    
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(token).getBody();
    }

    // 토큰 만료일자 조회
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    
    // 토근 만료 여부 체크
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // id를 입력받아 accessToken 생성
    public String generateAccessToken(Long id, String role) {

        final Date issuedAt = new Date();
        final Date accessTokenExpiresIn = new Date(issuedAt.getTime() + jwtProperties.getAccessExp() * 1000);

        return doGenerateAccessToken(id, issuedAt, accessTokenExpiresIn, role);
    }

    // JWT accessToken 생성
    private String doGenerateAccessToken(Long id, Date issuedAt, Date accessExpiresIn, String role) {
        
        final Key encodeKey = getSecertKey();
        
        return Jwts.builder()
                .setSubject(id.toString())
                .setIssuedAt(issuedAt)
                .setExpiration(accessExpiresIn)
                .setIssuer(jwtProperties.getIssuer())
                .claim(TYPE.getValue(), ACCESS_TOKEN)
                .claim(TOKEN_ROLE.getValue(), role)
                .signWith(encodeKey)
                .compact();
    }

    // refresh Token 생성
    public String generateRefreshToken(Long id) {

        final Date issuedAt = new Date();
        final Date refreshTokenExpiresIn = new Date(issuedAt.getTime() + jwtProperties.getRefreshExp() * 1000);

        return doGenerateRefreshToken(id, issuedAt, refreshTokenExpiresIn);
    }

    // JWT refreshToken 생성
    private String doGenerateRefreshToken(Long id, Date issuedAt, Date refreshTokenExpiresIn) {

        final Key encodedKey = getSecertKey();

        return Jwts.builder()
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(issuedAt)
                .setSubject(id.toString())
                .claim(TYPE.getValue(), REFRESH_TOKEN.getValue())
                .setExpiration(refreshTokenExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    // 토큰 형식 확인
    public boolean isAccessToken(String token) {
        return getJws(token).getBody().get(TYPE.getValue()).equals(ACCESS_TOKEN.getValue());
    }

    public boolean isRefreshToken(String token) {
        return getJws(token).getBody().get(TYPE.getValue()).equals(REFRESH_TOKEN);
    }

    public AccessTokenDetail parseAccessToken(String token) {
        if (isAccessToken(token)) {
            Claims claims = getJws(token).getBody();
            log.error("claims?>>> {}", claims);
            return AccessTokenDetail.builder()
                    .userId(Long.parseLong(claims.getSubject()))
                    .role((String) claims.get(TOKEN_ROLE.getValue()))
                    .build();
        }
        throw InvalidTokenException.EXCEPTION;
    }

    public Long parseRefreshToken(String token) {
        try {
            if (isRefreshToken(token)) {
                Claims claims = getJws(token).getBody();
                return Long.parseLong(claims.getSubject());
            }
        } catch (ExpiredTokenException e) {
            throw ExpiredTokenException.EXCEPTION;
        }
        throw InvalidTokenException.EXCEPTION;
    }

    public Long getRefreshTokenTTLSecond() {
        return jwtProperties.getRefreshExp();
    }
}
