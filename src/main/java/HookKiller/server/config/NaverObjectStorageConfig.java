package HookKiller.server.config;

import HookKiller.server.properties.NaverObjectStorageProperties;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class NaverObjectStorageConfig {
    private final NaverObjectStorageProperties naverObjectStorageProperties;

    @Bean
    public AmazonS3 storageObject() {
        log.info("Create Naver ObjectStorageConfig Bean");
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(this.getEndpointConfig())
                .withCredentials(this.getCredentialsProvier())
                .build();
    }

    private AwsClientBuilder.EndpointConfiguration getEndpointConfig() {
        log.info(
                "Create EndPoint Object >>> EndPoint : {}, RegionName : {}",
                this.naverObjectStorageProperties.getEndPoint(),
                this.naverObjectStorageProperties.getRegionName()
        );

        return new AwsClientBuilder.EndpointConfiguration(
                this.naverObjectStorageProperties.getEndPoint(),
                this.naverObjectStorageProperties.getRegionName()
        );
    }

    private AWSStaticCredentialsProvider getCredentialsProvier() {
        log.info(
                "Create CredentialsProvider  >>> AccessKey : {}, SecretKey : {}",
                this.naverObjectStorageProperties.getAccessKey(),
                this.naverObjectStorageProperties.getSecretKey()
        );

        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(
                        naverObjectStorageProperties.getAccessKey(),
                        naverObjectStorageProperties.getSecretKey()
                )
        );
    }
}
