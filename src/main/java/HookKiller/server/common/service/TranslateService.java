package HookKiller.server.common.service;

import HookKiller.server.common.dto.PapagoTextRequest;
import HookKiller.server.common.dto.PapagoTextResponse;
import HookKiller.server.common.exception.NaverErrorException;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.properties.PapagoProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TranslateService {

    private final PapagoProperties papagoProperties;
    private final ObjectMapper objectMapper;
    
    @Qualifier("papagoTextTranslateTemplate")
    private final RestTemplate textRestTemplate;
    
    @Qualifier("papagoHtmlTranslateTemplate")
    private final RestTemplate htmlRestTemplate;
    
    /**
     * @param source : 번역의 대상이 될 원래의 언어 종류
     * @param target : 번역 후 결과물로 나올 언어 종류
     * @param html : 번역의 대상이 되는 html
     * @return : html 형식의 번역이 된 String
     */
    public String naverPapagoHtmlTranslate(LanguageType source, LanguageType target, String html) {
        MultiValueMap<String, String> papagoRequestBody = new LinkedMultiValueMap<>();
        papagoRequestBody.add("source", source.getLanguageCode());
        papagoRequestBody.add("target", target.getLanguageCode());
        papagoRequestBody.add("html", html);

        HttpHeaders papagoRequestHeaders = new HttpHeaders();
        papagoRequestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        papagoProperties.getHeader().forEach(papagoRequestHeaders::set);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(papagoRequestBody, papagoRequestHeaders);

        ResponseEntity<String> response =
                htmlRestTemplate.postForEntity(URI.create(papagoProperties.getHtmlRequestUrl()), request, String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            log.info("result >>> {}", response.getBody());
            return response.getBody();
        }
        throw NaverErrorException.EXCEPTION;
    }
    
    public String naverPapagoTextTranslate(LanguageType source, LanguageType target, String content) {
        HttpHeaders papagoRequestHeaders = new HttpHeaders();
        papagoRequestHeaders.setContentType(MediaType.APPLICATION_JSON);
        papagoProperties.getHeader().forEach(papagoRequestHeaders::set);

        Map papagoRequestBody = objectMapper.convertValue(PapagoTextRequest.builder()
                .source(source.getLanguageCode())
                .target(target.getLanguageCode())
                .text(content)
                .build(), Map.class);

        HttpEntity request = new HttpEntity<>(papagoRequestBody, papagoRequestHeaders);

        ResponseEntity<PapagoTextResponse> response =
                textRestTemplate.postForEntity(URI.create(papagoProperties.getTextRequestUrl()), request, PapagoTextResponse.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            log.info("result >>> {}", response.getBody().getMessage().getResult().toString());
            return response.getBody().getMessage().getResult().getTranslatedText();
        }
        throw NaverErrorException.EXCEPTION;
    }

}

