package HookKiller.server.config.security;

import HookKiller.server.jwt.JwtAuthenticationEntryPoint;
import HookKiller.server.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    private static Map<HttpMethod, String[]> permitAllMappingList = new HashMap<>() {{
        put(HttpMethod.GET, new String[]{
                // Infra Path
                "/health",
                "/papago/test",

                "/notice",
                "/notice/{noticeArticleId}",

                "/article/list/{boardId}",
                "/article/{articleId}",
                "/article/popular/{boardId}",

                "/reply/{articleId}"
        });
        put(HttpMethod.POST, new String[] {
        });
        put(HttpMethod.PUT, new String[]{});
        put(HttpMethod.DELETE, new String[]{});
    }};


    private static Map<HttpMethod, String[]> authMappingList = new HashMap<>() {{
        put(HttpMethod.GET, new String[]{
                "/mypage",
                "/mypage/mylist/{searchType}",

                "/article/like/{articleId}"
        });
        put(HttpMethod.POST, new String[]{
                "/article",

                "/reply",

                "/file/image",
                "/file/images",

                "/article/like/{articleId}"
        });
        put(HttpMethod.PUT, new String[]{
                "/article",

                "/mypage",
                "/mypage/thumnail",

        });
        put(HttpMethod.DELETE, new String[]{
                "/article/{articleId}",

                "/reply/{replyId}"
        });
    }};

    private static Map<HttpMethod, String[]> adminMappingList = new HashMap<>() {{
        put(HttpMethod.GET, new String[]{
                "/admin/account/{userId}",
                "/admin/account/list/{role}",
                "/admin/account/article/{userId}",
                "/admin/account/reply/{userId}"
        });
        put(HttpMethod.POST, new String[]{
                "/admin/register",

                "/notice"
        });
        put(HttpMethod.PUT, new String[]{
                "/admin/account/status",

                "/notice"

        });
        put(HttpMethod.DELETE, new String[]{
                "/notice",
                "/notice/{noticeArticleId}"
        });
    }};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable) // 기본 로그인화면 사용안함
                .csrf(AbstractHttpConfigurer::disable) // csrf 사용안함
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용안함
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers(
                                "/auth/**",
                                "/auth/oauth/**",
                                "/auth/oauth/google/**",
                                "/auth/oauth/kakao/**",
                                "/verifyEmail",
                                "/health",
                                "/search/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET, permitAllMappingList.get(HttpMethod.GET)).permitAll()
                        .requestMatchers(HttpMethod.POST, permitAllMappingList.get(HttpMethod.POST)).permitAll()
                        .requestMatchers(HttpMethod.PUT, permitAllMappingList.get(HttpMethod.PUT)).permitAll()
                        .requestMatchers(HttpMethod.DELETE, permitAllMappingList.get(HttpMethod.DELETE)).permitAll()
                        .requestMatchers("/user/**").authenticated() // 인증이 되면 들어갈 수 있음
                        .requestMatchers(HttpMethod.GET, authMappingList.get(HttpMethod.GET)).authenticated()
                        .requestMatchers(HttpMethod.POST, authMappingList.get(HttpMethod.POST)).authenticated()
                        .requestMatchers(HttpMethod.PUT, authMappingList.get(HttpMethod.PUT)).authenticated()
                        .requestMatchers(HttpMethod.DELETE, authMappingList.get(HttpMethod.DELETE)).authenticated()
                        .requestMatchers(HttpMethod.GET, adminMappingList.get(HttpMethod.GET)).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, adminMappingList.get(HttpMethod.POST)).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, adminMappingList.get(HttpMethod.PUT)).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, adminMappingList.get(HttpMethod.DELETE)).hasAuthority("ADMIN")
                )
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource)) //CORS Spring Boot 설정
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    
}
