package HookKiller.server.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    // TODO : 추후 WhiteList 항목 코드 변경 필요
    private static String[] tempWhiteListArray = {
            "/health",
            "/hello",
            "/h2-console/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector intorsepector) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry -> {
                            authorizationManagerRequestMatcherRegistry
                                    .requestMatchers(
                                            new MvcRequestMatcher(intorsepector, "/health"),
                                            new MvcRequestMatcher(intorsepector, "/hello"),
                                            new MvcRequestMatcher(intorsepector, "/h2-console/**")
                                    )
                                    .permitAll()
                                    .anyRequest().authenticated();
                        })
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


}
