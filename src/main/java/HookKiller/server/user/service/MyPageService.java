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
import HookKiller.server.common.exception.BadTypeRequestException;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.dto.MyPageUserUpdateRequest;
import HookKiller.server.user.dto.MyPageUserResponse;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(propagation = Propagation.NESTED)
    public void updateUserInfo(MyPageUserUpdateRequest request) {
        User user = userUtils.getUser();

        // 변경이 존재하는 경우에만 변경한다.
        if(request.getEmail() != null && !user.getEmail().equalsIgnoreCase(request.getEmail()))
            user.setEmail(request.getEmail());
        if (request.getPassword() != null && !passwordEncoder.matches(request.getPassword(), user.getPassword()))
            user.setPassword(request.getPassword());
        if (request.getThumbnail() != null && !user.getThumbnail().equalsIgnoreCase(request.getThumbnail()))
            user.setThumbnail(request.getThumbnail());
        if(request.getNickName() != null && !user.getNickName().equals(request.getNickName()))
            user.setNickName(request.getNickName());
    }

    @Transactional(propagation = Propagation.NESTED)
    public void updateUserThumbnailPath(MyPageUserUpdateRequest request) {
        User user = userUtils.getUser();
        if (request.getThumbnail() != null && !user.getThumbnail().equalsIgnoreCase(request.getThumbnail()))
            user.setThumbnail(request.getThumbnail());
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
