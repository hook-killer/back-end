package HookKiller.server.common.service;

import HookKiller.server.common.dto.*;
import HookKiller.server.common.exception.NaverErrorException;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.properties.PapagoProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TranslateService {

    private final PapagoProperties papagoProperties;
    private final ObjectMapper objectMapper;

    public String naverPapagoHtmlTranslate(LanguageType source, LanguageType target, String html) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> papagoRequestBody = new LinkedMultiValueMap<>();
        papagoRequestBody.add("source", source.getLanguageCode());
        papagoRequestBody.add("target", target.getLanguageCode());
        papagoRequestBody.add("html", html);

//     Map papagoRequestBody = objectMapper.convertValue(PapagoHtmlRequest.builder()
//             .source(source.getLanguageCode())
//             .target(target.getLanguageCode())
//             .html(html)
//             .build(), Map.class);

//     HttpEntity request = new HttpEntity<>(papagoRequestBody, papagoRequestHeaders);

        HttpHeaders papagoRequestHeaders = new HttpHeaders();
        papagoRequestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        papagoProperties.getHeader().forEach(papagoRequestHeaders::set);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(papagoRequestBody, papagoRequestHeaders);

        ResponseEntity<String> response =
                restTemplate.postForEntity(URI.create(papagoProperties.getHtmlRequestUrl()), request, String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            log.info("result >>> {}", response.getBody());
            return response.getBody();
        }
        throw NaverErrorException.EXCEPTION;
    }

    public String naverPapagoTextTranslate(LanguageType source, LanguageType target, String content) {
        RestTemplate restTemplate = new RestTemplate();
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
                restTemplate.postForEntity(URI.create(papagoProperties.getTextRequestUrl()), request, PapagoTextResponse.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            log.info("result >>> {}", response.getBody().getMessage().getResult().toString());
            return response.getBody().getMessage().getResult().getTranslatedText();
        }
        throw NaverErrorException.EXCEPTION;
    }

}
