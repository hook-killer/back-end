package HookKiller.server.config;


import HookKiller.server.properties.CorsProperties;
import HookKiller.server.properties.JwtProperties;
import HookKiller.server.properties.NaverObjectStorageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(
    {
        JwtProperties.class,
        CorsProperties.class,
        NaverObjectStorageProperties.class
    }
)
@Configuration
public class ConfigurationProperties {
}
