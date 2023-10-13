package HookKiller.server.common.controller;

import HookKiller.server.common.service.TranslateService;
import HookKiller.server.common.type.LanguageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

// TODO : 추후 삭제 필요 예정, 해당 클래스통해 번역의뢰 하는거 아니고 서비스 사용방법 기록하려 한거에요, 여기 추가하지마세요. by.Jong1
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/papago")
public class PapagoSampleController {
    private final TranslateService translateService;

    @GetMapping("/test")
    public ResponseEntity<List<Object>> testSample() {

        return ResponseEntity.ok(new ArrayList<>() {{
            add(translateService.naverPapagoTextTranslate(LanguageType.KO, LanguageType.JP, "안녕하세요~~"));
            add(translateService.naverPapagoHtmlTranslate(LanguageType.KO, LanguageType.JP, "<div>안녕하세요. 파파고입니다.</div>"));
        }});
    }
}
