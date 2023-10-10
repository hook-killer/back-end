package HookKiller.server.common.exception;

import static HookKiller.server.common.exception.GlobalException.FILE_DELETE_ERROR;

public class FileDeleteException extends BaseException {

    public static final BaseException EXCEPTION = new FileDeleteException();

    private FileDeleteException() {super(FILE_DELETE_ERROR);}

}

