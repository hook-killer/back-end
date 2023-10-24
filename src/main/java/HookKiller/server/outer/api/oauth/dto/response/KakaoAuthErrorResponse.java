package HookKiller.server.outer.api.oauth.dto.response;

import HookKiller.server.common.exception.FeignException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import feign.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoAuthErrorResponse {

    private String error;
    private String errorCode;
    private String errorDescription;

    public static KakaoAuthErrorResponse from(Response res) {
        try (InputStream bodyIs = res.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(bodyIs, KakaoAuthErrorResponse.class);
        } catch (IOException e) {
            throw FeignException.EXCEPTION;
        }
    }
}
