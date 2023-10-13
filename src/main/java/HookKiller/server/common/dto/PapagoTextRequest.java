package HookKiller.server.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PapagoTextRequest {
    private String source;
    private String target;
    private String text;
    private String html;
    private String glossaryKey;
    private String replaceInfo;
    private Boolean honorific;
}
