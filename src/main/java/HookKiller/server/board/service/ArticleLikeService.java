package HookKiller.server.board.service;

import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleLike;
import HookKiller.server.board.exception.ArticleContentNotFoundException;
import HookKiller.server.board.repository.ArticleLikeRepository;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.board.type.ArticleLikeConstants;
import HookKiller.server.common.dto.CommonBooleanResultResponse;
import HookKiller.server.common.exception.DeleteFailException;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleLikeService {
    private final UserUtils userUtils;
    private final ArticleRepository articleRepository;
    private final ArticleLikeRepository articleLikeRepository;

    /**
     * 1. 사용자가 정상적인 인가가 되지 않은 경우에는 `SecurityContextNotFoundException`이 발생한다.<br />
     * 2. 사용자가 DB에 존재하지 않는 경우에는 `UserNotFoundException`이 발생한다.<br />
     * 3. 사용자의 상태가 ACTIVE가 아닌 경우에는 `UserNotActiveException`가 발생한다.<br />
     * 4. articleId의 게시물이 존재하지 않는 경우에는 `ArticleContentNotFoundException`가 발생한다.<br />
     * 5. 게시물에 좋아요를 누른 경우 result: true, 안누른경우 result: false로 반환한다.<br />
     *
     * @param articleId
     * @return
     */
    @Transactional(readOnly = true)
    public CommonBooleanResultResponse isUserArticleLike(Long articleId) {
        User user = userUtils.getUserIsStatusActive();
        Article article = this.findByArticleId(articleId);
        boolean result = articleLikeRepository.findByArticleAndUser(article, user).isPresent();

        log.info("IsUserArticleLike 조회 유저 >>> {} , 조회 요청 ArticleId >>> {}", user.getEmail(), article.getId());
        return CommonBooleanResultResponse.builder()
                .result(result)
                .message(result ? ArticleLikeConstants.EXISTS_ARTICLE_LIKE : ArticleLikeConstants.NOT_EXISTS_ARTICLE_LIKE)
                .build();
    }


    /**
     * 1. 사용자가 정상적인 인가가 되지 않은 경우에는 `SecurityContextNotFoundException`이 발생한다.<br />
     * 2. 사용자가 DB에 존재하지 않는 경우에는 `UserNotFoundException`이 발생한다.<br />
     * 3. 사용자의 상태가 ACTIVE가 아닌 경우에는 `UserNotActiveException`가 발생한다.<br />
     * 4. articleId의 게시물이 존재하지 않는 경우에는 `ArticleContentNotFoundException`가 발생한다.<br />
     * 5. 좋아요를 누르지 않았던 경우에는 좋아요가 실행되며, 좋아요를 눌렀던 경우에는 좋아요 정보가 삭제된다 <br />
     * 5-1. 좋아요 삭제를 실패한 경우에는 `DeleteFailException`가 발생한다.
     *
     * @param articleId
     * @return
     */
    @Transactional
    public String articleLikeProcess(Long articleId) {
        User user = userUtils.getUserIsStatusActive();
        Article article = this.findByArticleId(articleId);

        //true가 없는 케이스
        boolean isNotExistsArticleLike = articleLikeRepository.findByArticleAndUser(article, user).isEmpty();
        if (isNotExistsArticleLike) {
            log.info("Add Article Like : UserEmail >>> {}, ArticleId >>> {}", user.getEmail(), article.getId());
            articleLikeRepository.save(
                    ArticleLike.builder().article(article).user(user).build()
            );
            article.addLikeCount();
            return ArticleLikeConstants.ARTICLE_LIKE_RTN_MSG_INSERT_STATUS;
        }
        if (articleLikeRepository.deleteByArticleAndUser(article, user).isPresent()) {
            log.info("Delete Article Like : UserEmail >>> {}, ArticleId >>> {}", user.getEmail(), article.getId());
            article.minusLikeCount();
            return ArticleLikeConstants.ARTICLE_LIKE_RTN_MSG_DELETE_STATUS;
        }
        log.error("Delete Error ArticleLike : UserEmail >>> {} , ArticleId >>> {}", user.getEmail(), article.getId());
        throw DeleteFailException.EXCEPTION;
    }

    /**
     * articleId로 게시물을 조회한다.<br />
     * 만약 존재하지 않는 경우에는 `ArticleContentNotFoundException`가 발생한다.
     *
     * @param articleId
     * @return
     */
    private Article findByArticleId(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION);
    }
}
