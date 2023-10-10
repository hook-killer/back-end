package HookKiller.server.config.security;

import HookKiller.server.properties.CorsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CorsConfig {
    private final CorsProperties corsProperties;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        corsProperties.getDomains().forEach(url -> log.info("CORS Allowed URL : {}", url));

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(corsProperties.getDomains());
        config.setAllowedMethods(List.of(CorsConfiguration.ALL));
        config.setAllowedHeaders(List.of(CorsConfiguration.ALL));
        config.setExposedHeaders(List.of(LOCATION, SET_COOKIE));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}


