package HookKiller.server.properties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ToString
@AllArgsConstructor
@ConfigurationProperties(prefix = "naver.storage")
public class NaverObjectStorageProperties {
    private String endPoint;
    private String regionName;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}

