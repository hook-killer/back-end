package HookKiller.server.common.exception;


import static HookKiller.server.common.exception.GlobalException.MAIL_SEND_ERROR;

public class MailSendException extends BaseException {
    public static final MailSendException EXCEPTION = new MailSendException();

    public MailSendException() {
        super(MAIL_SEND_ERROR);
    }
}
