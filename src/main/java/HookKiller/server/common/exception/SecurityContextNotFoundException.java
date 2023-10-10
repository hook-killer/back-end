package HookKiller.server.common.exception;

public class SecurityContextNotFoundException extends BaseException {

    public static final BaseException EXCEPTION = new SecurityContextNotFoundException();

    private SecurityContextNotFoundException() {
        super(GlobalException.SECURITY_CONTEXT_NOT_FOUND_ERROR);
    }
}