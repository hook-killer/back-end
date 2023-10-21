package HookKiller.server.board.service;

import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.dto.PopularArticleResponse;
import HookKiller.server.board.dto.PostArticleRequestDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
import HookKiller.server.board.entity.Board;
import HookKiller.server.board.exception.ArticleContentNotFoundException;
import HookKiller.server.board.exception.ArticleUpdateUnauthorizedUserException;
import HookKiller.server.board.exception.BoardNotFoundException;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.board.repository.BoardRepository;
import HookKiller.server.board.type.ArticleConstants;
import HookKiller.server.common.dto.CommonBooleanResultResponse;
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

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
     * @param requestDto
     */
    @Transactional
    public void createArticle(PostArticleRequestDto requestDto) {

        User user = userUtils.getUser();
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        Article article = articleRepository.save(
                Article.builder()
                        .board(board)
                        .orgArticleLanguage(requestDto.getOrgArticleLanguage())
                        .articleStatus(PUBLIC)
                        .createdUser(user)
                        .updatedUser(user)
                        .build()
        );

        List<ArticleContent> articleContentList = new ArrayList<>();
        articleContentList.add(
                ArticleContent.builder()
                        .article(article)
                        .language(requestDto.getOrgArticleLanguage())
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .build()
        );

        requestDto
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
                                                        requestDto.getOrgArticleLanguage(), targetLanguage, requestDto.getTitle()
                                                )
                                        ).content(
                                                translateService.naverPapagoHtmlTranslate(
                                                        requestDto.getOrgArticleLanguage(), targetLanguage, requestDto.getContent()
                                                )
                                        ).build()
                        )

                );
        articleContentRepository.saveAll(articleContentList);
    }

    /**
     * 1. 사용자가 정상적인 인가가 되지 않은 경우에는 `SecurityContextNotFoundException`이 발생한다.<br />
     * 2. 사용자가 DB에 존재하지 않는 경우에는 `UserNotFoundException`이 발생한다.<br />
     * 3. 사용자의 상태가 ACTIVE가 아닌 경우에는 `UserNotActiveException`가 발생한다.<br />
     * 4. articleId의 게시물이 존재하지 않는 경우에는 `ArticleContentNotFoundException`가 발생한다.<br />
     * 5. 요청한 사용자가 작성자 또는 관리자가 아닌 경우에는 `ArticleUpdateUnauthorizedUserException`가 발생한다. <br />
     * 6. Papago Translation을 실행하던 중 Exception이 발생하면 수정과정은 Rollback된다.
     * @param requestDto
     */
    @Transactional
    public void updateArticle(PostArticleRequestDto requestDto) {
        User user = userUtils.getUserIsStatusActive();
        Article article = articleRepository.findById(requestDto.getArticleId())
                .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION);

        if (!article.getCreatedUser().equals(user) || !user.getRole().equals(UserRole.ADMIN)) {
            throw ArticleUpdateUnauthorizedUserException.EXCEPTION;
        }

        // 변경여부 확인을 위한 변수
        boolean chgTitle = false;
        boolean chgContent = false;

        List<ArticleContent> contents = article.getArticleContent();

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
                    .filter(content -> !content.equals(articleContent))
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

    /**
     * 1. 사용자가 정상적인 인가가 되지 않은 경우에는 `SecurityContextNotFoundException`이 발생한다.<br />
     * 2. 사용자가 DB에 존재하지 않는 경우에는 `UserNotFoundException`이 발생한다.<br />
     * 3. 사용자의 상태가 ACTIVE가 아닌 경우에는 `UserNotActiveException`가 발생한다.<br />
     * 4. articleId의 게시물이 존재하지 않는 경우에는 `ArticleContentNotFoundException`가 발생한다.<br />
     * 5. 삭제가 성공적으로 이뤄진경우 result: true와 함께 성공메세지가 반환되며, 실패한 경우 result: false와 함께 실패 메세지가 반환된다.
     *
     * @param articleId
     * @return
     */
    @Transactional
    public CommonBooleanResultResponse deleteArticle(Long articleId) {
        User user = userUtils.getUserIsStatusActive();
        Article article = articleRepository.findById(articleId).orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION);

        // 삭제 권한 확인
        if (article.getCreatedUser().equals(user) || user.getRole().equals(UserRole.ADMIN)) {
            article.updateStatus(DELETE);
            return CommonBooleanResultResponse.builder().result(true).message(ArticleConstants.ARTICLE_DELETE_SUCCESS_RTN_MSG).build();
        }
        return CommonBooleanResultResponse.builder().result(false).message(ArticleConstants.ARTICLE_DELETE_FAIL_RTN_MSG).build();
    }

    public List<PopularArticleResponse> getPopularArticlesByBoardId(int page, int limit, Long boardId, LanguageType language) {
        // 현재 시간을 가져와 Timestamp로 변환
        Instant currentInstant = Instant.now();
        Timestamp currentTimestamp = Timestamp.from(currentInstant);

        // 7일 전의 시간을 계산하고 Timestamp로 변환
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        Instant sevenDaysAgoInstant = sevenDaysAgo.atZone(ZoneId.systemDefault()).toInstant();
        Timestamp sevenDaysAgoTimestamp = Timestamp.from(sevenDaysAgoInstant);

        Board board = boardRepository.findById(boardId).orElseThrow(() -> BoardNotFoundException.EXCEPTION);
        
        return articleRepository
                .findAllByBoardAndArticleStatusAndCreateAtBetweenOrderByLikeCountDesc(board , PUBLIC, sevenDaysAgoTimestamp, currentTimestamp, PageRequest.of(page, limit))
                .stream()
                .map(article -> {
                    ArticleContent content =  articleContentRepository.findByArticleAndLanguage(article, language).orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION);
                    return PopularArticleResponse.builder()
                            .articleId(article.getId())
                            .title(content.getTitle())
                            .build();
                })
                .toList();
    }
}
