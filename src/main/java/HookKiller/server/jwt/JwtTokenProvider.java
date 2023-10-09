package HookKiller.server.jwt;

import HookKiller.server.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class JwtTokenProvider {
    private final String secretKey;
    private final Long accessExp;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.secretKey = jwtProperties.getSecretKey();
        this.accessExp = jwtProperties.getAccessExp();
    }

    // token으로 사용자 id 조회
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
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
    public String generateToken(String userId) {
        return generateAccessToken(userId, new HashMap<>());
    }

    // id, 속성정보를 이용해 accessToken 생성
    public String generateAccessToken(String userId, Map<String, Object> claims) {
        return doGenerateAccessToken(userId, claims);
    }

    // JWT accessToken 생성
    private String doGenerateAccessToken(String userId, Map<String, Object> claims) {
        log.debug("doGenerateToken : SecretKey >>> {}, AccessKey >>> {}", this.secretKey, this.accessExp);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.accessExp * 1000))
                .signWith(SignatureAlgorithm.HS512, this.secretKey)
                .compact();
    }

    // 토근 검증
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
