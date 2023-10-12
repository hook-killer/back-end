package HookKiller.server.common.type;

import HookKiller.server.common.exception.IllegalArgumentException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum LanguageType {
    KO, EN, CN, JP;

    public static LanguageType findType(String value) {
        if (value == null || value.isEmpty()) {
            return KO;
//            throw IllegalArgumentException.EXCEPTION;
        }
        return Arrays.stream(LanguageType.values())
                .filter(type -> type.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> IllegalArgumentException.EXCEPTION);
    }

    public static LanguageType findTypeByRequest(HttpServletRequest request) {
        if(request == null)
            return KO;
//            throw IllegalArgumentException.EXCEPTION;
        return findType(request.getHeader(RequestHeaderConstants.KEY_LANGUAGE));
    }
}
