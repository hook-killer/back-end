package HookKiller.server.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "hook.mail")
public class HookMailProperties {
    private String registerDomain;
}
