package HookKiller.server.common.exception;

public class FeignException extends BaseException {
    public static final BaseException EXCEPTION = new FeignException();

    private FeignException() {
        super(GlobalException.FEIGN_SERVER_ERROR);
    }
}
