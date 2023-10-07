package HookKiller.server.board.service;

import HookKiller.server.board.dto.ArticleDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.board.repository.BoardRepository;
import HookKiller.server.common.exception.ExampleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final BoardRepository boardRepository;
  private final ArticleRepository articleRepository;
  private final ArticleContentRepository articleContentRepository;

  @Transactional(readOnly = true)
  public List<ArticleDto> getArticleList(Long boardId, String language) {
    return articleRepository.findAllByBoard(boardRepository.findById(boardId).orElseThrow(() -> ExampleException.EXCEPTION))
            .stream()
            .map(article -> ArticleDto.of(article, articleContentRepository.findByArticleAndLanguage(article, language).orElseThrow(() -> ExampleException.EXCEPTION)))
            .toList();
  }
}
