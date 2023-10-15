package HookKiller.server.board.service;

import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.dto.PostArticleRequestDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.board.entity.Board;
import HookKiller.server.board.exception.ArticleContentNotFoundException;
import HookKiller.server.board.exception.ArticleDeleteUnauthorizedUserException;
import HookKiller.server.board.exception.ArticleUpdateUnauthorizedUserException;
import HookKiller.server.board.exception.BoardNotFoundException;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.board.repository.BoardRepository;
import HookKiller.server.common.service.TranslateService;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.type.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static HookKiller.server.common.type.ArticleStatus.DELETE;
import static HookKiller.server.common.type.ArticleStatus.PUBLIC;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

  private final UserUtils userUtils;
  private final BoardRepository boardRepository;
  private final ArticleRepository articleRepository;
  private final ArticleContentRepository articleContentRepository;
  private final TranslateService translateService;

  /**
   * 리스트 조회
   *
   * @param page
   * @param articleLimit
   * @param boardId
   * @param language
   * @return
   */
  @Transactional(readOnly = true)
  public List<ArticleRequestDto> getArticleList(int page, int articleLimit, Long boardId, LanguageType language) {
    // boardId로 board에 해당하는 Article들을 모두 뽑아온다
    Board board = boardRepository.findById(boardId).orElseThrow(() -> BoardNotFoundException.EXCEPTION);
    Page<Article> articleList = articleRepository.findAllByBoardAndArticleStatusOrderByCreateAtDesc(board, PUBLIC, PageRequest.of(page, articleLimit));
    return articleList.stream()
            .map(article ->
                    ArticleRequestDto.of(article, articleContentRepository
                            .findByArticleAndLanguage(article, language)
                            .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION)))
            .toList();
  }

  /**
   * 단건 조회
   *
   * @param articleId
   * @param language
   * @return
   */
  @Transactional(readOnly = true)
  public ArticleRequestDto getArticleByArticleId(Long articleId, LanguageType language) {
    Article article = articleRepository.findByIdAndArticleStatus(articleId, PUBLIC)
            .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION);
    ArticleContent content = articleContentRepository.findByArticleAndLanguage(article, language)
            .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION);

    return ArticleRequestDto.of(article, content);
  }

  /**
   * 게시물 등록
   *
   * @param postArticleRequestDto
   * @param board
   */
  @Transactional
  public void createArticle(PostArticleRequestDto postArticleRequestDto, Board board) {

    User user = userUtils.getUser();

    Article article = articleRepository.save(
            Article.builder()
                    .board(board)
                    .orgArticleLanguage(postArticleRequestDto.getOrgArticleLanguage())
                    .articleStatus(PUBLIC)
                    .createdUser(user)
                    .updatedUser(user)
                    .build()
    );

    List<ArticleContent> articleContentList = new ArrayList<>();
    articleContentList.add(
            ArticleContent.builder()
                    .article(article)
                    .language(postArticleRequestDto.getOrgArticleLanguage())
                    .title(postArticleRequestDto.getTitle())
                    .content(postArticleRequestDto.getContent())
                    .build()
    );

    postArticleRequestDto
            .getOrgArticleLanguage()
            .getTranslateTargetLanguage()
            .forEach(targetLanguage ->
                    articleContentList.add(
                            ArticleContent
                                    .builder()
                                    .article(article)
                                    .language(targetLanguage)
                                    .title(
                                            translateService.naverPapagoTextTranslate(
                                                    postArticleRequestDto.getOrgArticleLanguage(), targetLanguage, postArticleRequestDto.getTitle()
                                            )
                                    ).content(
                                            translateService.naverPapagoHtmlTranslate(
                                                    postArticleRequestDto.getOrgArticleLanguage(), targetLanguage, postArticleRequestDto.getContent()
                                            )
                                    ).build()
                    )

            );
    articleContentRepository.saveAll(articleContentList);
  }


  @Transactional
  public void updateArticle(PostArticleRequestDto requestDto) {

    User user = userUtils.getUser();

    Article article = articleRepository.findByIdAndArticleStatus(requestDto.getArticleId(), PUBLIC)
            .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION);

    User author = article.getCreatedUser();

    // 수정하는 유저 권한 확인
    if (!user.getId().equals(author.getId()))
      if (!user.getRole().equals(UserRole.ADMIN))
        throw ArticleUpdateUnauthorizedUserException.EXCEPTION;

    // 변경여부 확인을 위한 변수
    boolean chgTitle = false;
    boolean chgContent = false;

    List<ArticleContent> contents = articleContentRepository.findAllByArticle(article);

    article.setUpdatedUser(user);

    if (!requestDto.getOrgArticleLanguage().equals(article.getOrgArticleLanguage()))
      article.setOrgArticleLanguage(requestDto.getOrgArticleLanguage());

    ArticleContent articleContent = contents.stream()
            .filter(content -> requestDto.getOrgArticleLanguage().equals(content.getLanguage()))
            .findFirst()
            .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION);

    if (requestDto.getNewTitle() != null && !requestDto.getNewTitle().equals(requestDto.getTitle())) {
      chgTitle = true;
      articleContent.setTitle(requestDto.getNewTitle());
    }
    if (requestDto.getNewContent() != null && requestDto.getNewContent().equals(requestDto.getContent())) {
      chgContent = true;
      articleContent.setContent(requestDto.getContent());
    }

    if (chgTitle || chgContent) {
      final boolean finalChgTitle = chgTitle;
      final boolean finalChgContent = chgContent;
      contents.stream()
              .filter(content -> articleContent != content)
              .forEach(content -> {
                        if (finalChgTitle) {
                          translateService.naverPapagoTextTranslate(articleContent.getLanguage(), content.getLanguage(), articleContent.getTitle());
                        }
                        if (finalChgContent) {
                          translateService.naverPapagoHtmlTranslate(articleContent.getLanguage(), content.getLanguage(), articleContent.getContent());
                        }
                      }
              );
    }
  }


  @Transactional
  public void deleteArticle(ArticleRequestDto requestDto) {

    User user =  userUtils.getUser();
    User author = requestDto.getCreatedUser();

    // 삭제 권한 확인
    if (!user.getId().equals(author.getId()))
      if (!user.getRole().equals(UserRole.ADMIN))
        throw ArticleDeleteUnauthorizedUserException.EXCEPTION;

    articleRepository
            .findById(requestDto.getArticleId())
            .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION)
            .updateStatus(DELETE);
  }

}
