package HookKiller.server.board.service;

import HookKiller.server.board.dto.ArticleDto;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.board.repository.BoardRepository;
import HookKiller.server.common.exception.ExampleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final BoardRepository boardRepository;
  private final ArticleRepository articleRepository;
  private final ArticleContentRepository articleContentRepository;

  public List<ArticleDto> getArticleList(Long boardId, String language) {
    // boardId로 board에 해당하는 Article들을 모두 뽑아온다
    // Article들 하나하나마다에 해당하는 ArticleContent랑 같이 DTO로 변환한다
    // 변환된 DTO들을 모아놓은 list를 리턴한다
    return articleRepository
            .findAllByBoard(boardRepository.findById(boardId).orElseThrow(() -> ExampleException.EXCEPTION))
            .stream().map(article -> ArticleDto.of(article, articleContentRepository.findByArticleAndLanguage(article, language).orElseThrow(() -> ExampleException.EXCEPTION)))
            .toList();
  }
}
