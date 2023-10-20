package HookKiller.server.properties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
@ConfigurationProperties(prefix = "papago")
public class PapagoProperties {
    private String  textRequestUrl;
    private String  htmlRequestUrl;
    private Map<String, String> header;
}
