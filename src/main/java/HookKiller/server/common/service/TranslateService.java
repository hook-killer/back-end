package HookKiller.server.common.service;

import HookKiller.server.common.dto.TranslateResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TranslateService {

  // @Value()
  private String clientId;
  // @Value()
  private String clientSecret;

    public TranslateResponseDto.Result naverPapagoTranslate(String source, String target, String content) {

    WebClient webClient = WebClient.builder()
            .baseUrl("https://")
            .build();
    TranslateResponseDto response = webClient.post().uri(
                    uriBuilder -> uriBuilder.queryParam("source", source)
                            .queryParam("target", target)
                            .queryParam("text", content)
                            .build()
            )
            .header("X-Naver-Client-Id", clientId)
            .header("X-Naver-Client-Secret", clientSecret)
            .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .retrieve()
            .bodyToMono(TranslateResponseDto.class).block();
    return response.getMessage().getResult();
  }

}

