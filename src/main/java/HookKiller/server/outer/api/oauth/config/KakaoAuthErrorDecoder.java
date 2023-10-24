package HookKiller.server.outer.api.oauth.config;

import HookKiller.server.common.dto.ErrorDetail;
import HookKiller.server.common.exception.OuterServerException;
import HookKiller.server.outer.api.oauth.dto.response.KakaoAuthErrorResponse;
import HookKiller.server.outer.api.oauth.exception.KakaoAuthErrorCode;
import feign.Response;
import feign.codec.ErrorDecoder;

public class KakaoAuthErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        KakaoAuthErrorResponse body = KakaoAuthErrorResponse.from(response);

        try {
            KakaoAuthErrorCode kakaoKauthErrorCode =
                    KakaoAuthErrorCode.valueOf(body.getErrorCode());
            ErrorDetail errorDetail = kakaoKauthErrorCode.getErrorDetail();
            throw new OuterServerException(
                    errorDetail.getStatusCode(),
                    errorDetail.getErrorCode(),
                    errorDetail.getReason());
        } catch (IllegalArgumentException e) {
            KakaoAuthErrorCode koeInvalidRequest = KakaoAuthErrorCode.KOE_INVALID_REQUEST;
            ErrorDetail errorDetail = koeInvalidRequest.getErrorDetail();
            throw new OuterServerException(
                    errorDetail.getStatusCode(),
                    errorDetail.getErrorCode(),
                    errorDetail.getReason());
        }
    }
}
