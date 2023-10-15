package HookKiller.server.board.service;

import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.dto.PostArticleRequestDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.board.entity.Board;
import HookKiller.server.board.exception.ArticleContentNotFoundException;
import HookKiller.server.board.exception.BoardNotFoundException;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.board.repository.BoardRepository;
import HookKiller.server.board.type.ArticleStatus;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static HookKiller.server.board.type.ArticleStatus.PUBLIC;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

  private final UserUtils userUtils;
  private final ArticleContentService articleContentService;
  private final BoardRepository boardRepository;
  private final ArticleRepository articleRepository;
  private final ArticleContentRepository articleContentRepository;

  public List<ArticleRequestDto> getArticleList(int page, int articleLimit, Long boardId, LanguageType language) {
    // boardId로 board에 해당하는 Article들을 모두 뽑아온다
    Board board = boardRepository.findById(boardId).orElseThrow(()-> BoardNotFoundException.EXCEPTION);
    Page<Article> articleList = articleRepository.findAllByBoardAndArticleStatusOrderByCreateAtDesc(board, PUBLIC, PageRequest.of(page, articleLimit));
    return articleList.stream()
            .map(article ->
                    ArticleRequestDto.of(article, articleContentRepository
                            .findByArticleAndLanguage(article, language)
                            .orElseThrow(()-> ArticleContentNotFoundException.EXCEPTION)))
            .toList();
  }

  @Transactional(readOnly = true)
  public ArticleRequestDto getArticleByArticleId(Long articleId, LanguageType language) {
    Article article = articleRepository.findByIdAndArticleStatus(articleId, PUBLIC)
            .orElseThrow(()-> ArticleContentNotFoundException.EXCEPTION);
    ArticleContent content = articleContentRepository.findByArticleAndLanguage(article, language)
            .orElseThrow(()-> ArticleContentNotFoundException.EXCEPTION);
    return ArticleRequestDto.of(article, content);
  }

  public Article createArticle(PostArticleRequestDto postArticleRequestDto) {
    Board board = boardRepository.findById(postArticleRequestDto.getBoardId())
            .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

    User requestUser = userUtils.getUser();
    return articleRepository.save(Article.builder()
            .board(board)
            .articleStatus(ArticleStatus.PUBLIC)
            .orgArticleLanguage(postArticleRequestDto.getOrgArticleLanguage())
            .createdUser(requestUser)
            .updatedUser(requestUser)
            .build()
    );
  }

  @Transactional
  public Article updateArticle(PostArticleRequestDto postArticleRequestDto) {

    Article article = articleRepository.findById(postArticleRequestDto.getArticleId())
            .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION);

    User requestUser = userUtils.getUser();

    article.setOrgArticleLanguage(postArticleRequestDto.getOrgArticleLanguage());
    article.setUpdatedUser(requestUser);

    // 게시물 내용 업데이트
    articleContentService.updateContent(postArticleRequestDto, article);

    return articleRepository.save(article);
  }



  @Transactional
  public void deleteArticle(Long articleId) {
    articleRepository
            .findById(articleId)
            .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION)
            .updateStatus(ArticleStatus.DELETE);
  }
}
