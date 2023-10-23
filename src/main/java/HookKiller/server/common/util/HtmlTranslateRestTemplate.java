package HookKiller.server.common.util;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class HtmlTranslateRestTemplate {
  
  @Bean
  @Qualifier("papagoHtmlTranslateTemplate")
  public RestTemplate htmlTranslate() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setHttpClient(this.httpClient());
    
    RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(20))
            .build();
    
    restTemplate.setRequestFactory(factory);
    
    return restTemplate;
  }
  
  private CloseableHttpClient httpClient() {
    return HttpClientBuilder
            .create()
            .setConnectionManager(this.papagoHtmlHttpClient())
            .build();
  }
  private PoolingHttpClientConnectionManager papagoHtmlHttpClient() {
    return PoolingHttpClientConnectionManagerBuilder.create()
            .setMaxConnTotal(50)
            .setMaxConnPerRoute(5)
            .build();
  }
  
}
