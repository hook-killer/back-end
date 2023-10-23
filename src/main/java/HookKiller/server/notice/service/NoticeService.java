package HookKiller.server.notice.service;

import HookKiller.server.board.dto.PostArticleRequestDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.ArticleContent;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static HookKiller.server.common.type.ArticleStatus.DELETE;
import static HookKiller.server.common.type.ArticleStatus.PUBLIC;
import static HookKiller.server.common.type.LanguageType.CN;
import static HookKiller.server.common.type.LanguageType.EN;
import static HookKiller.server.common.type.LanguageType.JP;
import static HookKiller.server.common.type.LanguageType.KO;

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
     *
     * @return
     */
    @Transactional(readOnly = true)
    public Map<String, Object> getNoticeList(int page, int articleLimit, LanguageType languageType) {
        Page<NoticeArticle> pageResult = noticeArticleRepository.findAllByStatusOrderByCreateAtDesc(PUBLIC, PageRequest.of(page, articleLimit));
        Map<String, Object> result = new HashMap<>();
        result.put("totalPage", pageResult.getTotalPages());
        result.put("totalElements", pageResult.getTotalElements());
        result.put("data", pageResult
                .stream()
                .filter(noticeArticle -> noticeArticle.getContent().stream().anyMatch(noticeArticleContent -> noticeArticleContent.getLanguage().equals(languageType)))
                .map(noticeArticle -> {
                    //그래봐야 Filter로 존재하는 애들만 걸러져서 의미없음
                    NoticeContent noticeContent = noticeArticle.getContent()
                            .stream()
                            .filter(content -> content.getLanguage().equals(languageType))
                            .findFirst()
                            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

                    NoticeArticleDto listDtoResult = NoticeArticleDto.builder()
                            .id(noticeArticle.getId())
                            .language(noticeArticle.getLanguage())
                            .status(noticeArticle.getStatus())
                            .createdUser(noticeArticle.getCreatedUser())
                            .updatedUser(noticeArticle.getUpdatedUser())
                            .title(noticeContent.getTitle())
                            .build();
                    listDtoResult.setCreateAt(noticeArticle.getCreateAt());
                    listDtoResult.setUpdateAt(noticeArticle.getUpdateAt());
                    return listDtoResult;
                })
                .toList());
        return result;
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
        LanguageType orgLanguageType = addNoticeRequest.getLanguage();

        //로그인한 사용자 획득
        User user = userUtils.getUser();

        //관리자 권한 확인
        if (!user.getRole().equals(UserRole.ADMIN))
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
        if (orgLanguageType.equals(KO) || orgLanguageType.equals(JP)) {
            addNoticeRequest
                    .getLanguage()
                    .getTranslateTargetLanguage()
                    .forEach(targetLanguage -> contentsList.add(
                            buildArticleContentByLanguage(targetLanguage, noticeArticle, addNoticeRequest, addNoticeRequest.getContent())
                    ));
        } else {
            NoticeContent koreaContent = buildArticleContentByLanguage(KO, noticeArticle, addNoticeRequest, addNoticeRequest.getContent());
            NoticeContent japanContent = buildArticleContentByLanguage(JP, noticeArticle, addNoticeRequest, addNoticeRequest.getContent());
            NoticeContent otherContent = buildArticleContentByKoreaToTargetLang(orgLanguageType.equals(CN) ? EN : CN, noticeArticle, addNoticeRequest, koreaContent.getContent());
            contentsList.add(koreaContent);
            contentsList.add(japanContent);
            contentsList.add(otherContent);
        }
        noticeContentRepository.saveAll(contentsList);
    }

    /**
     * orgLanguage(source 언어 타입)를 languageType(target 언어 타입)으로 번역한 결과물을 가지고 ArticleContent로 만들기
     *
     * @param languageType     target 언어 타입
     * @param noticeArticle    content에 해당하는 article
     * @param addNoticeRequest request로 온 내용물
     * @param content          target 언어로 번역된 NoticeContent
     * @return ArticleConent
     */
    private NoticeContent buildArticleContentByLanguage(LanguageType languageType, NoticeArticle noticeArticle, AddNoticeRequest addNoticeRequest, String content) {
        return NoticeContent
                .builder()
                .noticeArticle(noticeArticle)
                .language(languageType)
                .title(
                        translateService.naverPapagoTextTranslate(
                                addNoticeRequest.getLanguage(), languageType, addNoticeRequest.getTitle()
                        )
                ).content(
                        translateService.naverPapagoHtmlTranslate(addNoticeRequest.getLanguage(), languageType, content)
                )
                .build();
    }

    private  NoticeContent buildArticleContentByKoreaToTargetLang(LanguageType languageType, NoticeArticle noticeArticle, AddNoticeRequest addNoticeRequest, String content) {
        return NoticeContent
                .builder()
                .noticeArticle(noticeArticle)
                .language(languageType)
                .title(
                        translateService.naverPapagoTextTranslate(
                                addNoticeRequest.getLanguage(), languageType, addNoticeRequest.getTitle()
                        )
                ).content(
                        translateService.naverPapagoHtmlTranslate(KO, languageType, content)
                )
                .build();
    }


    /**
     * 게시물 수정
     * 1. 사용자가 로그인을 하지 않은 경우 → UserNotFoundException이 발생한다.
     * 2. 사용자가 로그인을 하였지만 관리자가 아닌 경우 → NoticeNotAdminForbiddenException이 발생한다.
     * 3. 공지사항 게시물이 NoticeArticleId와 공개상태인지로 조회시 존재하지 않는 경우 → NoticeNotFoundException이 발생한다.
     * 4. 요청한 언어가 DB에 없는 경우 → NoticeNotFoundException이 발생한다
     * 5. 번역이 실패한 경우 → NaverErrorException이 발생한다.
     * <p>
     * 어떤 경우도 Exception이 발생하면 수정이 적용되지 않고 Rollback된다.
     *
     * @param request
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateNotice(EditNoticeRequest request) {
        //로그인한 사용자 획득
        User user = userUtils.getUser();

        //관리자 권한 확인
        if (!user.getRole().equals(UserRole.ADMIN))
            throw NoticeNotAdminForbiddenException.EXCEPTION;

        // 변경여부 확인을 위한 변수
        boolean chgTitle = false;
        boolean chgContent = false;

        LanguageType orgLanguageType = request.getLanguage();

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

        if (chgTitle) {
            //Lambda에서 활용하기 위한 final변수 변환
            final boolean finalChgTitle = chgTitle;
            contents.stream()
                    .filter(content -> choiceContent != content)
                    .forEach(content -> {
                                if (finalChgTitle) {
                                    content.setTitle(translateService.naverPapagoTextTranslate(choiceContent.getLanguage(), content.getLanguage(), choiceContent.getTitle()));
                                }
                            }
                    );
        }

        if (orgLanguageType.equals(KO) || orgLanguageType.equals(JP)) {
            contents.stream()
                    .filter(content -> choiceContent != content)
                    .forEach(content -> content.setContent(translateService.naverPapagoHtmlTranslate(choiceContent.getLanguage(), content.getLanguage(), request.getNewContent())));
        } else {
            String koResult = translateService.naverPapagoHtmlTranslate(orgLanguageType, KO, request.getNewContent());
            String jpResult = translateService.naverPapagoHtmlTranslate(orgLanguageType, JP, request.getNewContent());
            String otherResult = translateService.naverPapagoHtmlTranslate(KO, orgLanguageType.equals(CN) ? EN : CN, koResult);

            if (chgContent) {
                for (NoticeContent content : contents) {
                    if (content.getLanguage().equals(KO)) {
                        content.setContent(koResult);
                    } else if (content.getLanguage().equals(JP)) {
                        content.setContent(jpResult);
                    } else {
                        if (content.getLanguage().equals(request.getLanguage())) {
                            content.setContent(request.getNewContent());
                        } else {
                            content.setContent(otherResult);
                        }
                    }
                }
            }
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
        if (!user.getRole().equals(UserRole.ADMIN))
            throw NoticeNotAdminForbiddenException.EXCEPTION;

        noticeArticleRepository.findById(id)
                .orElseThrow(
                        () -> NoticeNotFoundException.EXCEPTION
                ).updateStatus(DELETE);
    }

}
