package HookKiller.server.common.exception;


import static HookKiller.server.common.exception.GlobalException.NAVER_ERROR;

public class NaverErrorException extends BaseException {

    public static final BaseException EXCEPTION = new NaverErrorException();

    private NaverErrorException() {
        super(NAVER_ERROR);
    }

}