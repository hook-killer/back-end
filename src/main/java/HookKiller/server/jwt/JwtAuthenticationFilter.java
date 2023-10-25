package HookKiller.server.jwt;

import HookKiller.server.auth.exception.InvalidTokenException;
import HookKiller.server.auth.exception.TokenException;
import HookKiller.server.auth.exception.TokenNotFoundException;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.auth.service.CustomUserDetails;
import HookKiller.server.common.dto.AccessTokenDetail;
import HookKiller.server.properties.JwtProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;


    @Value("${auth.jwt.header}") private String HEADER_STRING;
    @Value("${auth.jwt.prefix}") private String TOKEN_PREFIX;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 토근을 가져옴
        String header = request.getHeader(HEADER_STRING);
        log.info("header >>> {}", header);
        String authToken = null;

        // Bearer token인 경우 JWT 토큰 유효성 검사 진행
        // AuthenticationManager 역할을 함. -> 토큰이 인증되어있으면 필터로 보내서 context에 저장. 토큰이 인증되어있지 않다면 AuthenticationProvider로 보내어 인증하도록 함.
        // token 검증이 되고 인증 정보가 존재하지 않는 경우 spring security 인증 정보 저장
        if (header != null && header.startsWith(TOKEN_PREFIX) && !header.equalsIgnoreCase(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX, "");
            try {
                AccessTokenDetail accessTokenDetail = jwtTokenProvider.parseAccessToken(authToken);

                UserDetails userDetails =
                        CustomUserDetails.of(
                                accessTokenDetail.getUserId().toString(), accessTokenDetail.getRole());

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                userDetails, "user", userDetails.getAuthorities())
                );
            } catch (IllegalArgumentException ex) {
                throw UserNotFoundException.EXCEPTION;
            } catch (ExpiredJwtException ex) {
                throw TokenNotFoundException.EXCEPTION;
            } catch (MalformedJwtException ex) {
                throw InvalidTokenException.EXCEPTION;
            } catch (Exception e) {
                throw TokenException.EXCEPTION;
            }
        }
        filterChain.doFilter(request, response);
    }

    // Filter에서 제외할 URL 설정
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return jwtProperties.getExcludePath().stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
    }
}
