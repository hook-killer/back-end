package HookKiller.server.notice.service;

import HookKiller.server.common.service.TranslateService;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.notice.dto.AddNoticeRequest;
import HookKiller.server.notice.dto.EditNoticeRequest;
import HookKiller.server.notice.dto.NoticeArticleDto;
import HookKiller.server.notice.entity.NoticeArticle;
import HookKiller.server.notice.entity.NoticeContent;
import HookKiller.server.notice.exception.NoticeNotAdminForbiddenException;
import HookKiller.server.notice.exception.NoticeNotFoundException;
import HookKiller.server.notice.repository.NoticeArticleRepository;
import HookKiller.server.notice.repository.NoticeContentRepository;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.type.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class NoticeService {
    private final TranslateService translateService;
    private final NoticeArticleRepository noticeArticleRepository;
    private final NoticeContentRepository noticeContentRepository;
    private final UserUtils userUtils;

    /**
     * 단건 조회
     *
     * @param noticeArticleId
     * @param languageType
     * @return
     */
    @Transactional(readOnly = true)
    public NoticeArticleDto getNoticeArticleByArticleId(Long noticeArticleId, LanguageType languageType) {
        NoticeArticle article = noticeArticleRepository.findByIdAndStatus(noticeArticleId, PUBLIC)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
        NoticeContent content = noticeContentRepository.findByNoticeArticleAndLanguage(article, languageType)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
        return NoticeArticleDto.of(article, content);
    }

    /**
     * 리스트 조회 - 사용자가 요청한 languageType에 맞춰서 List조회
     * 1. ArticleStatus가 공개중(PUBLIC)인 상태
     * 2. 생성일이 최신 순서로
     * 3. Content에서 선택한 언어로 번역된 데이터가 존재하는 경우
     * TODO : 페이지네이션
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<NoticeArticleDto> getNoticeList(int page, int articleLimit, LanguageType languageType) {
        return noticeArticleRepository.findAllByStatusOrderByCreateAtDesc(PUBLIC, PageRequest.of(page, articleLimit))
                .stream()
                .filter(noticeArticle -> noticeArticle.getContent().stream().anyMatch(noticeArticleContent -> noticeArticleContent.getLanguage().equals(languageType)))
                .map(noticeArticle -> {
                    //그래봐야 Filter로 존재하는 애들만 걸러져서 의미없음
                    NoticeContent noticeContent = noticeArticle.getContent()
                            .stream()
                            .filter(content -> content.getLanguage().equals(languageType))
                            .findFirst()
                            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
                    return NoticeArticleDto.builder()
                            .id(noticeArticle.getId())
                            .language(noticeArticle.getLanguage())
                            .status(noticeArticle.getStatus())
                            .createdUser(noticeArticle.getCreatedUser())
                            .updatedUser(noticeArticle.getUpdatedUser())
                            .title(noticeContent.getTitle())
                            .build();
                })
                .toList();
    }

    /**
     * 공지사항 게시물 등록
     * 1. 사용자가 로그인을 하지 않은 경우 → UserNotFoundException이 발생한다.
     * 2. 사용자가 로그인을 하였지만 관리자가 아닌 경우 → NoticeNotAdminForbiddenException이 발생한다.
     * 3. 번역이 실패한 경우 → NaverErrorException이 발생한다.
     *
     * @param addNoticeRequest
     */
    @Transactional
    public void saveNotice(AddNoticeRequest addNoticeRequest) {
        //로그인한 사용자 획득
        User user = userUtils.getUser();

        //관리자 권한 확인
        if(user.getRole().equals(UserRole.ADMIN))
            throw NoticeNotAdminForbiddenException.EXCEPTION;

        NoticeArticle noticeArticle = noticeArticleRepository.save(
                NoticeArticle.builder()
                        .language(addNoticeRequest.getLanguage())
                        .status(PUBLIC)
                        .createdUser(user)
                        .updatedUser(user)
                        .build()
        );

        List<NoticeContent> contentsList = new ArrayList<>();
        contentsList.add(
                NoticeContent.builder()
                        .noticeArticle(noticeArticle)
                        .language(addNoticeRequest.getLanguage())
                        .title(addNoticeRequest.getTitle())
                        .content(addNoticeRequest.getContent())
                        .build()
        );

        addNoticeRequest
                .getLanguage()
                .getTranslateTargetLanguage()
                .forEach(targetLanguage ->
                        contentsList.add(
                                NoticeContent
                                        .builder()
                                        .noticeArticle(noticeArticle)
                                        .language(targetLanguage)
                                        .title(
                                                translateService.naverPapagoTextTranslate(
                                                        addNoticeRequest.getLanguage(), targetLanguage, addNoticeRequest.getTitle()
                                                )
                                        ).content(
                                                translateService.naverPapagoHtmlTranslate(
                                                        addNoticeRequest.getLanguage(), targetLanguage, addNoticeRequest.getContent()
                                                )
                                        ).build()
                        )
                );

        noticeContentRepository.saveAll(contentsList);
    }

    /**
     * 게시물 수정
     * 1. 사용자가 로그인을 하지 않은 경우 → UserNotFoundException이 발생한다.
     * 2. 사용자가 로그인을 하였지만 관리자가 아닌 경우 → NoticeNotAdminForbiddenException이 발생한다.
     * 3. 공지사항 게시물이 NoticeArticleId와 공개상태인지로 조회시 존재하지 않는 경우 → NoticeNotFoundException이 발생한다.
     * 4. 요청한 언어가 DB에 없는 경우 → NoticeNotFoundException이 발생한다
     * 5. 번역이 실패한 경우 → NaverErrorException이 발생한다.
     *
     * 어떤 경우도 Exception이 발생하면 수정이 적용되지 않고 Rollback된다.
     *
     * @param request
     */
    @Transactional
    public void updateNotice(EditNoticeRequest request) {
        //로그인한 사용자 획득
        User user = userUtils.getUser();

        //관리자 권한 확인
        if(user.getRole().equals(UserRole.ADMIN))
            throw NoticeNotAdminForbiddenException.EXCEPTION;

        // 변경여부 확인을 위한 변수
        boolean chgTitle = false;
        boolean chgContent = false;

        //게시물이 공개중이어야 수정가능함
        NoticeArticle article = noticeArticleRepository.findByIdAndStatus(request.getNoticeArticleId(), PUBLIC)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
        List<NoticeContent> contents = noticeContentRepository.findAllByNoticeArticle(article);


        article.setUpdatedUser(user);
        //다른경우 변경
        if (!request.getLanguage().equals(article.getLanguage()))
            article.setLanguage(request.getLanguage());

        NoticeContent choiceContent = contents.stream()
                .filter(content -> request.getLanguage().equals(content.getLanguage()))
                .findFirst()
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        if (request.getNewTitle() != null && !request.getNewTitle().equals(request.getOrgTitle())) {
            chgTitle = true;
            choiceContent.setTitle(request.getNewTitle());
        }
        if (request.getNewContent() != null && !request.getNewContent().equals(request.getOrgContent())) {
            chgContent = true;
            choiceContent.setContent(request.getNewContent());
        }

        if (chgTitle || chgContent) {
            //Lambda에서 활용하기 위한 final변수 변환
            final boolean finalChgTitle = chgTitle;
            final boolean finalChgContent = chgContent;
            contents.stream()
                    .filter(content -> choiceContent != content)
                    .forEach(content -> {
                                if (finalChgTitle) {
                                    translateService.naverPapagoTextTranslate(choiceContent.getLanguage(), content.getLanguage(), choiceContent.getTitle());
                                }
                                if (finalChgContent) {
                                    translateService.naverPapagoHtmlTranslate(choiceContent.getLanguage(), content.getLanguage(), choiceContent.getContent());
                                }
                            }
                    );
        }

    }

    /**
     * 공지사항 게시물 삭제
     * 1. 사용자가 로그인을 하지 않은 경우 → UserNotFoundException이 발생한다.
     * 2. 사용자가 로그인을 하였지만 관리자가 아닌 경우 → NoticeNotAdminForbiddenException이 발생한다.
     *
     * @param id
     */
    @Transactional
    public void deleteNotice(Long id) {
        //로그인한 사용자 획득
        User user = userUtils.getUser();

        //관리자 권한 확인
        if(user.getRole().equals(UserRole.ADMIN))
            throw NoticeNotAdminForbiddenException.EXCEPTION;

        noticeArticleRepository.findById(id).orElseThrow(() ->
                NoticeNotFoundException.EXCEPTION).updateStatus(DELETE);
    }

}
