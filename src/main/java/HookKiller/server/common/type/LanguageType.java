package HookKiller.server.common.type;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum LanguageType {
    KO("ko"), EN("en"), CN("zh-CN"), JP("ja");

    private String languageCode;

    private static final String HTTP_REQUEST_HEADER_KEY_LANGUAGE = "language";

    public static LanguageType findType(String value) {
        return (value == null || value.isEmpty()) ?
                KO : Arrays.stream(LanguageType.values())
                .filter(type -> type.name().equals(value.toUpperCase()))
                .findFirst().orElse(KO);
    }

    public static LanguageType findTypeByRequest(HttpServletRequest request) {
        return request == null ? KO : findType(request.getHeader(HTTP_REQUEST_HEADER_KEY_LANGUAGE));
    }

    public Set<LanguageType> getTranslateTargetLanguage() {
        return Arrays.stream(LanguageType.values())
                .filter(languageType -> !languageType.equals(this))
                .collect(Collectors.toSet());
    }
}
