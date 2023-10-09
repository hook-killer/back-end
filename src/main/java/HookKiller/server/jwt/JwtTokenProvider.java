package HookKiller.server.jwt;

import HookKiller.server.properties.JwtProperties;
import HookKiller.server.user.type.UserRole;
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
    
    public static final String TOKEN_ID = "userId";
    public static final String TOKEN_EMAIL = "email";
    public static final String TOKEN_PASSWORD = "password";
    public static final String TOKEN_ROLE = "role";

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.secretKey = jwtProperties.getSecretKey();
        this.accessExp = jwtProperties.getAccessExp();
    }
    
    public String getUserIdFromToken(String token) {
        return getValueByFieldName(token, TOKEN_ID);
    }

    // token으로 사용자 id 조회
    public String getUsernameFromToken(String token) {
        return getValueByFieldName(token, TOKEN_EMAIL);
    }
    
    public String getUserRoleFromToken(String token) {
        return getValueByFieldName(token, TOKEN_ROLE);
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
    public String generateToken(Long id, String email,  UserRole role) {
        Map<String, String> claimMap = new HashMap<>();
        claimMap.put(TOKEN_ID, id.toString());
        claimMap.put(TOKEN_EMAIL, email);
        claimMap.put(TOKEN_ROLE, role.name());
        return generateAccessToken(email, claimMap);
    }

    // id, 속성정보를 이용해 accessToken 생성
    public String generateAccessToken(String email, Map<String, String> claims) {
        return doGenerateAccessToken(email, claims);
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

    // 토큰 검증
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
