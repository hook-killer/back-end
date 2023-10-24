package HookKiller.server.search.service;

import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.search.dto.SimpleArticleVo;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
  
  private final UserRepository userRepository;
  private final ArticleRepository articleRepository;
  private final ArticleContentRepository articleContentRepository;
  
  public List<SimpleArticleVo> getSearchResult(LanguageType languageType, String word, PageRequest pageRequest) {
    return articleRepository.retrieveArticleListDown(languageType.name(), word, pageRequest)
            .stream()
            .map(SimpleArticleVo::from)
            .toList();
  }
  
  public List<SimpleArticleVo> getAllSearchResultByWord(LanguageType languageType, String word) {
    return articleRepository.retrieveAllArticleByWord(languageType.name(), word).stream().map(SimpleArticleVo::from).toList();
  }
}

