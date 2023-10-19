package HookKiller.server.user.service;

import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.dto.ReplyResponseDto;
import HookKiller.server.board.exception.ArticleContentNotFoundException;
import HookKiller.server.board.exception.ReplyContentNotFoundException;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.repository.ArticleLikeRepository;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.board.repository.ReplyContentRepository;
import HookKiller.server.board.repository.ReplyRepository;
import HookKiller.server.common.dto.CommonBooleanResultResponse;
import HookKiller.server.common.exception.BadTypeRequestException;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.dto.MyPageUserUpdateRequest;
import HookKiller.server.user.dto.MyPageUserResponse;
import HookKiller.server.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static HookKiller.server.common.util.SecurityUtils.passwordEncoder;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserUtils userUtils;
    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;
    private final ArticleLikeRepository articleLikeRepository;
    private final ReplyContentRepository replyContentRepository;
    private final ArticleContentRepository articleContentRepository;

    @Transactional(readOnly = true)
    public MyPageUserResponse getMyPage() {
        return MyPageUserResponse.from(userUtils.getUser());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserInfo(MyPageUserUpdateRequest request) {
        User user = userUtils.getUser();

        // 변경이 존재하는 경우에만 변경한다.
        if (request.getPassword() != null && !passwordEncoder.matches(request.getPassword(), user.getPassword()))
            user.setPassword(request.getPassword());
        if(request.getNickName() != null && !user.getNickName().equals(request.getNickName()))
            user.setNickName(request.getNickName());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CommonBooleanResultResponse updateUserThumbnailPath(MyPageUserUpdateRequest request) {
        User user = userUtils.getUser();
        log.error("request Thumnail >>> {} , userThumnail >>> {}", request.getThumbnail(), user.getThumbnail());
        if(request.getThumbnail() == null)
            return CommonBooleanResultResponse.builder().result(false).message("요청 Path가 없습니다.").build();
        if(request.getThumbnail().trim().equals(""))
            return CommonBooleanResultResponse.builder().result(false).message("요청 Path가 없습니다.").build();
        if(user.getThumbnail() == null || !request.getThumbnail().equalsIgnoreCase(user.getThumbnail())) {
            user.setThumbnail(request.getThumbnail());
            return CommonBooleanResultResponse.builder().result(true).message("수정이 완료되었습니다.").build();
        }
        return CommonBooleanResultResponse.builder().result(false).message("동일한 Path라 수정이 불가능합니다.").build();
    }



    @Transactional(readOnly = true)
    public Object getMyCreatedList(int page, int limit, String searchType, LanguageType language) {
        User user = userUtils.getUser();
        Pageable pageable = PageRequest.of(page, limit);
        if(searchType.equalsIgnoreCase("ARTICLE")) {
            return articleRepository.findAllByCreatedUserOrderByCreateAtDesc(user, pageable)
                    .stream().map(
                            article -> ArticleRequestDto.of(article, articleContentRepository
                                    .findByArticleAndLanguage(article, language)
                                    .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION))
                    ).toList();
        }

        if(searchType.equalsIgnoreCase("REPLY")){
            return replyRepository.findAllByCreatedUserOrderByCreateAtDesc(user, pageable)
                    .stream()
                    .map(reply ->
                            ReplyResponseDto.of(
                                    reply,
                                    replyContentRepository.findByReplyAndLanguage(reply, language)
                                            .orElseThrow(() -> ReplyContentNotFoundException.EXCEPTION)
                            )
                    ).toList();
        }

        if(searchType.equalsIgnoreCase("LIKE")) {
            return articleLikeRepository
                    .findAllByUserOrderByCreateAtDesc(user, pageable)
                    .map(articleLike -> ArticleRequestDto.of(articleLike.getArticle(), articleContentRepository
                            .findByArticleAndLanguage(articleLike.getArticle(), language)
                            .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION))
                    ).toList();
        }

        throw BadTypeRequestException.EXCEPTION;
    }



}
