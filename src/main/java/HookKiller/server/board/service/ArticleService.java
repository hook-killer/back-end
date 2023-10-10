package HookKiller.server.board.service;

import HookKiller.server.board.dto.ArticleDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.Board;
import HookKiller.server.board.exception.ArticleContentNotFoundException;
import HookKiller.server.board.exception.BoardNotFoundException;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.board.repository.BoardRepository;
import HookKiller.server.board.type.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ArticleService {

  private final BoardRepository boardRepository;
  private final ArticleRepository articleRepository;
  private final ArticleContentRepository articleContentRepository;

  public List<ArticleDto> getArticleList(Long boardId, BoardType language) {
    // boardId로 board에 해당하는 Article들을 모두 뽑아온다
    Board board = boardRepository.findById(boardId).orElseThrow(()-> BoardNotFoundException.EXCEPTION);
    List<Article> articleList = articleRepository.findAllByBoard(board);

    // Article들 하나하나 마다에 해당하는 ArticleContent랑 같이 DTO로 변환한다
    List<ArticleDto> articleDtoList = new ArrayList<>();

    return articleList.stream()
            .map(article ->
                    ArticleDto.of(article, articleContentRepository.findByArticleAndLanguage(article, language)
                            .orElseThrow(()-> ArticleContentNotFoundException.EXCEPTION)))
            .toList();
  }

}
