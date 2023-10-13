package HookKiller.server.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class PapagoTextResponse {

    private PapagoMessage message;
    @Getter
    @Setter
    public static class PapagoMessage{
        private PapagoResult result;
    }

    @Getter
    @Setter
    @ToString
    public static class PapagoResult {
        private String srcLangType;
        private String tarLangType;
        private String translatedText;
    }
}

