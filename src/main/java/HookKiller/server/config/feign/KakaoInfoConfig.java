package HookKiller.server.config.feign;

import HookKiller.server.outer.api.oauth.config.KakaoInfoErrorDecoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(KakaoInfoErrorDecoder.class)
public class KakaoInfoConfig {

    @Bean
    @ConditionalOnMissingBean(value = ErrorDecoder.class)
    public KakaoInfoErrorDecoder commonFeignErrorDecoder() {
        return new KakaoInfoErrorDecoder();
    }

    @Bean
    Encoder formEncoder() {
        return new feign.form.FormEncoder();
    }
}
