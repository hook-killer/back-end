package HookKiller.server.common.exception;


import HookKiller.server.common.dto.ErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private BaseErrorCode errorCode;

    public ErrorDetail getErrorDetail() {
        return this.errorCode.getErrorDetail();
    }
}
