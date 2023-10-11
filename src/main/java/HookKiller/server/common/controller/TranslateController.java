package HookKiller.server.common.controller;

import HookKiller.server.common.dto.TranslateResponseDto;
import HookKiller.server.common.service.TranslateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class TranslateController {
  private final TranslateService translateService;

  @PostMapping("/api/translate")
  public TranslateResponseDto.Result naverPapagoTranslate(String source, String target, String text) {
    return translateService.naverPapagoTranslate(source, target, text);
  }
}
