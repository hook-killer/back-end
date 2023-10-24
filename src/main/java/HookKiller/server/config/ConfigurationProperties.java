package HookKiller.server.config;


import HookKiller.server.properties.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(
    {
        JwtProperties.class,
        CorsProperties.class,
        NaverObjectStorageProperties.class,
        KakaoOauthProperties.class,
        PapagoProperties.class,
        HookMailProperties.class,
        GoogleOauthProperties.class
    }
)
@Configuration
public class ConfigurationProperties {
}
