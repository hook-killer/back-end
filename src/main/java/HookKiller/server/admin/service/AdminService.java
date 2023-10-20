package HookKiller.server.admin.service;

import HookKiller.server.admin.dto.AccountSearchResponse;
import HookKiller.server.auth.dto.request.SingUpRequest;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.board.dto.ReplyResponseDto;
import HookKiller.server.board.exception.ArticleContentNotFoundException;
import HookKiller.server.board.exception.ReplyContentNotFoundException;
import HookKiller.server.board.repository.ArticleContentRepository;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.board.repository.ReplyContentRepository;
import HookKiller.server.board.repository.ReplyRepository;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.dto.UserDto;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.AlreadyExistUserException;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.Status;
import HookKiller.server.user.type.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static HookKiller.server.user.type.UserRole.ADMIN;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserUtils userUtils;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;
    private final ReplyContentRepository replyContentRepository;
    private final ArticleContentRepository articleContentRepository;


    /**
     * 관리자 계정 등록
     */
    @Transactional
    public void regAdmin(SingUpRequest registerRequest) {

        User requestAdmin = userUtils.verificationRequestUserAdminAndGetUser();
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw AlreadyExistUserException.EXCEPTION;
        }

        User user = userRepository.save(
                User.builder()
                        .email(registerRequest.getEmail())
                        .nickName(registerRequest.getNickName())
                        .password(registerRequest.getPassword())
                        .role(ADMIN)
                        .build()
        );
        log.info("관리자 계정 등록 : 계정생성자 >>> {}, 생성계정 >>> {}", requestAdmin.getEmail(), user.getEmail());
    }

    /**
     * 사용자의 세부 정보를 획득한다. <br />
     * 1. 인증, 인가가 되지 않은 경우 `SecurityContextNotFoundException`가 발생한다. <br />
     * 2. 사용자가 DB에 존재하지 않는 경우 `UserNotFoundException`가 발생한다. <br />
     * 3. 사용자가 관리자가 아닌 경우에는 `UserNotAdminException`가 발생한다. <br />
     * 4. 파라미터로 받은 userId가 존재하지 않는 사용자인 경우에는 ` UserNotFoundException`가 발생한다. <br />
     * 5. 존재하는 경우 `AccountSearchResponse`타입으로 반환한다. <br />
     *
     * @param userId
     * @return
     */
    public AccountSearchResponse getAccountDetails(Long userId) {
        userUtils.verificationRequestUserAdmin();
        return AccountSearchResponse.of(
                userRepository.findById(userId)
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION)
        );
    }


    /**
     * Role따른 계정 조회 <br />
     * 1. 인증, 인가가 되지 않은 경우 `SecurityContextNotFoundException`가 발생한다. <br />
     * 2. 사용자가 DB에 존재하지 않는 경우 `UserNotFoundException`가 발생한다. <br />
     * 3. 사용자가 관리자가 아닌 경우에는 `UserNotAdminException`가 발생한다. <br />
     * 4. 일치하는 조건의 계정 정보에 대해서 List로 limit갯수만큼 반환한다. <br />
     *
     * @param role
     * @return
     */
    @Transactional(readOnly = true)
    public List<UserDto> acctListByRole(int page, int limit, Status userStatus, UserRole role) {
        userUtils.verificationRequestUserAdmin();
        log.info("계정 리스트 >>> {}", role);
        return userRepository.findAllByRoleAndStatusOrderByCreateAtDesc(role, userStatus, PageRequest.of(page, limit))
                .stream()
                .map(UserDto::from)
                .toList();
    }

    /**
     * 계정 상태 변경
     */
    @Transactional
    public void modifyAcctStat(Long id, Status status) {
        userUtils.verificationRequestUserAdmin();
        log.info("계정 {} >>> {}", status, id);

        userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION)
                .updateUserStatus(status);
    }

    /**
     * UserId의 사용자가 작성한 게시물을 작성 최신순으로 List로 반환한다.
     * @param page
     * @param limit
     * @param languageType
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    public List<ArticleRequestDto> getArticleListByUserId(int page, int limit, LanguageType languageType, Long userId) {
        userUtils.verificationRequestUserAdmin();
        User targetUser = userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION);


        log.info("조회 대상 사용자 ID >>> {}", userId);
        return articleRepository.findAllByCreatedUserOrderByCreateAtDesc(targetUser, PageRequest.of(page, limit))
                .stream()
                .map(article ->
                        ArticleRequestDto.of(
                                article, articleContentRepository.findByArticleAndLanguage(article, languageType)
                                        .orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION)
                        )
                ).toList();
    }

    /**
     * 사용자의 댓글 리스트를 LanguageType, 최신순으로 조회
     * @param page 조회 희망 페이지
     * @param limit
     * @param languageType
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    public List<ReplyResponseDto> getReplyListByUserId(int page, int limit, LanguageType languageType, Long userId) {
        userUtils.verificationRequestUserAdmin();
        User targetUser = userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        log.info("조회 대상 사용자 ID >>> {}", userId);
        return replyRepository.findAllByCreatedUserOrderByCreateAtDesc(targetUser, PageRequest.of(page, limit))
                .stream()
                .map(reply ->
                        ReplyResponseDto.of(
                                reply,
                                replyContentRepository.findByReplyAndLanguage(reply, languageType)
                                        .orElseThrow(() -> ReplyContentNotFoundException.EXCEPTION)
                        )
                ).toList();
    }
}
