package HookKiller.server.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LanguageType {
    KO("ko"), EN("en"), CN("zh-CN"), JP("ja");

    private String languageCode;
}
