package HookKiller.server.jwt;

import HookKiller.server.properties.JwtProperties;
import HookKiller.server.user.type.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static HookKiller.server.jwt.ClaimVal.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private String secretKey;
    private Long accessExp;

    private final JwtProperties jwtProperties;
    
    public String getUserIdFromToken(String token) {
        return getValueByFieldName(token, TOKEN_ID.getValue());
    }

    // token으로 사용자 id 조회
    public String getUsernameFromToken(String token) {
        return getValueByFieldName(token, TOKEN_EMAIL.getValue());
    }
    
    public String getUserRoleFromToken(String token) {
        return getValueByFieldName(token, TOKEN_ROLE.getValue());
    }
    
    public String getUserNickNameFromToken(String token) {
        return getValueByFieldName(token, TOKEN_NICKNAME.getValue());
    }
    
    private String getValueByFieldName(String token, String claimName) {
        return getClaimFromToken(token, claims -> claims.get(claimName)).toString();
    }

    // 모든 token에 대한 사용자 속성정보 조회
    private Claims getAllClaimsFromToken(String token) {
        log.debug("TokenUtil SecretKey >>> {} ", this.secretKey);
        return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
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
    public String generateAccessToken(Long id, String email, String nickname, UserRole role) {
        Map<String, String> claimMap = new HashMap<>();
        claimMap.put(TOKEN_ID.getValue(), id.toString());
        claimMap.put(TOKEN_EMAIL.getValue(), email);
        claimMap.put(TOKEN_ROLE.getValue(), role.name());
        claimMap.put(TOKEN_NICKNAME.getValue(), nickname);
        return doGenerateAccessToken(email, claimMap);
    }

    // JWT accessToken 생성
    // UsernamePasswordAuthenticationToken
    private String doGenerateAccessToken(String email, Map<String, String> claims) {
        log.debug("doGenerateToken : SecretKey >>> {}, AccessKey >>> {}", this.secretKey, this.accessExp);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.accessExp * 1000))
                .signWith(SignatureAlgorithm.HS512, this.secretKey)
                .compact();
    }

    // TODO : AccessToken의 생성방식과 동일하게 변경
    public String generateRefreshToken(Long id) {
        final Date issuedAt = new Date();
        final Date refreshTokenExpiresIn = new Date(issuedAt.getTime() + jwtProperties.getRefreshExp() * 1000);
        Map<String, String> claimMap = new HashMap<>();
        return doGenerateRefreshToken(id, issuedAt, refreshTokenExpiresIn);
    }

    // TODO : AccessToken의 생성방식과 동일하게 변경
    private String doGenerateRefreshToken(Long id, Date issuedAt, Date refreshTokenExpiresIn) {
        final Key encodedKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(issuedAt)
                .setSubject(id.toString())
                .claim(TYPE.getValue(), REFRESH_TOKEN.getValue())
                .setExpiration(refreshTokenExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    // 토큰 검증
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Long getRefreshTokenTTLSecond() {
        return jwtProperties.getRefreshExp();
    }
}
