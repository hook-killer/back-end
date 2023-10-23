package HookKiller.server.common.controller;

import HookKiller.server.common.service.TranslateService;
import HookKiller.server.common.type.LanguageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// TODO : 추후 삭제 필요 예정, 해당 클래스 통해 번역의뢰 하는거 아니고 서비스 사용방법 기록하고 테스트 해보려고 한거에요, 여기 추가하지마세요. by.Jong1 & Bong
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/papago")
public class PapagoSampleController {
    private final TranslateService translateService;

    @GetMapping("/test")
    public ResponseEntity<List<Object>> testSample() {

        return ResponseEntity.ok(new ArrayList<>() {{
            add(translateService.naverPapagoTextTranslate(LanguageType.KO, LanguageType.JP, "안녕하세요~~"));
            add(translateService.naverPapagoTextTranslate(LanguageType.EN, LanguageType.KO, "Hello, I'm Papago."));
            add(translateService.naverPapagoTextTranslate(LanguageType.JP, LanguageType.KO, "こんにちは。パパゴです。"));
            add(translateService.naverPapagoTextTranslate(LanguageType.CN, LanguageType.KO, "你好，我是帕帕戈。"));
            
            add(translateService.naverPapagoTextTranslate(LanguageType.JP, LanguageType.CN, "こんにちは。パパゴです。"));
            add(translateService.naverPapagoTextTranslate(LanguageType.JP, LanguageType.EN, "こんにちは。パパゴです。"));
            
            add(translateService.naverPapagoTextTranslate(LanguageType.CN, LanguageType.JP, "你好，我是帕帕戈。"));
            add(translateService.naverPapagoTextTranslate(LanguageType.CN, LanguageType.EN, "你好，我是帕帕戈。"));
            
            add(translateService.naverPapagoTextTranslate(LanguageType.EN, LanguageType.JP, "Hello, I'm Papago."));
            add(translateService.naverPapagoTextTranslate(LanguageType.EN, LanguageType.CN, "Hello, I'm Papago."));
            
            add(translateService.naverPapagoHtmlTranslate(LanguageType.KO, LanguageType.JP, "<div>안녕하세요. 파파고입니다.</div>"));
            add(translateService.naverPapagoHtmlTranslate(LanguageType.EN, LanguageType.KO, "<div>Hello, I'm Papago.</div>"));
            add(translateService.naverPapagoHtmlTranslate(LanguageType.JP, LanguageType.KO, "<div>こんにちは。パパゴです。</div>"));
            add(translateService.naverPapagoHtmlTranslate(LanguageType.CN, LanguageType.KO, "<div>你好，我是帕帕戈。</div>"));
            
            add(translateService.naverPapagoHtmlTranslate(LanguageType.JP, LanguageType.CN, "<div>こんにちは。パパゴです。</div>"));
            add(translateService.naverPapagoHtmlTranslate(LanguageType.JP, LanguageType.EN, "<div>こんにちは。パパゴです。</div>"));
            
            add(translateService.naverPapagoHtmlTranslate(LanguageType.CN, LanguageType.JP, "<div>你好，我是帕帕戈。</div>"));
//            add(translateService.naverPapagoHtmlTranslate(LanguageType.CN, LanguageType.EN, "<div>你好，我是帕帕戈。</div>"));
            
            add(translateService.naverPapagoHtmlTranslate(LanguageType.EN, LanguageType.JP, "<div>Hello, I'm Papago.</div>"));
//            add(translateService.naverPapagoHtmlTranslate(LanguageType.EN, LanguageType.CN, "<div>Hello, I'm Papago.</div>"));
        }});
    }
}
