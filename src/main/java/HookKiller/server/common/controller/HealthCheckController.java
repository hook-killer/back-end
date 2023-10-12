package HookKiller.server.common.controller;

import HookKiller.server.common.service.TranslateService;
import HookKiller.server.common.type.LanguageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HealthCheckController {
    private final TranslateService translateService;
    @GetMapping("/health")
    public ResponseEntity healthCheck(){
        translateService.naverPapagoTextTranslate(LanguageType.KO, LanguageType.JP, "안녕하세요~~");
        translateService.naverPapagoHtmlTranslate(LanguageType.KO, LanguageType.JP, "<div>안녕하세요. 파파고입니다.</div>");

        log.error("authentication : {}", SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok().build();
    }

}
