package HookKiller.server.jwt;

import HookKiller.server.auth.service.CustomUserDetails;
import HookKiller.server.auth.service.CustomUserDetailsService;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;
    

    @Value("${auth.jwt.header}") private String HEADER_STRING;
    @Value("${auth.jwt.prefix}") private String TOKEN_PREFIX;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("request: {}", request.getHeader(HEADER_STRING));

        // 토근을 가져옴
        String header = request.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;
        
        // Bearer token인 경우 JWT 토큰 유효성 검사 진행
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX," ");
            try {
                username = this.jwtTokenProvider.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException ex) {
                log.info("fail get user id");
            } catch (ExpiredJwtException ex) {
                log.info("Token expired");
            } catch (MalformedJwtException ex) {
                log.info("Invalid JWT !!");
            } catch (Exception e) {
                log.info("Unable to get JWT Token !!");
            }
        } else {
            log.info("JWT does not begin with Bearer !!");
        }

        // AuthenticationManager 역할을 함. -> 토큰이 인증되어있으면 필터로 보내서 context에 저장. 토큰이 인증되어있지 않다면 AuthenticationProvider로 보내어 인증하도록 함.
        // token 검증이 되고 인증 정보가 존재하지 않는 경우 spring security 인증 정보 저장
        if ((username != null) && (SecurityContextHolder.getContext().getAuthentication() == null)) {
            try {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (this.jwtTokenProvider.validateToken(authToken, userDetails)) {
                    
                    // 유저아이디 비밀번호 토큰 인증
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    
                    authenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (UsernameNotFoundException e) {
                throw e;
                // TODO: custom exception 던지도록 바꾸기
            }
        } else {
            log.error("Username is null or context is not null !!");
        }
        filterChain.doFilter(request, response);
    }

    // Filter에서 제외할 URL 설정
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        jwtProperties.getExcludePath().forEach(path -> log.error("path: {}\n", path));
        return jwtProperties.getExcludePath().stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
    }
}
