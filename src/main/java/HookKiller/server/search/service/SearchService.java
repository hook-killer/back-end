package HookKiller.server.search.service;

import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.repository.ArticleRepository;
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
  
  public List<SimpleArticleVo> getSearchResult(String word, PageRequest pageRequest) {
    return articleRepository.retrieveArticleListDown(word, pageRequest)
            .stream()
            .map(SimpleArticleVo::from)
            .toList();
  }
  
  public List<SimpleArticleVo> getAllSearchResultByWord(String word) {
    return articleRepository.retrieveAllArticleByWord(word).stream().map(SimpleArticleVo::from).toList();
  }
}

