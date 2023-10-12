package HookKiller.server.service;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.notice.dto.AddNoticeRequest;
import HookKiller.server.notice.dto.EditNoticeRequest;
import HookKiller.server.notice.dto.NoticeArticleDto;
import HookKiller.server.notice.entity.NoticeArticle;
import HookKiller.server.notice.entity.NoticeContent;
import HookKiller.server.notice.exception.NoticeNotFoundException;
import HookKiller.server.repository.NoticeArticleRepository;
import HookKiller.server.repository.NoticeContentRepository;
import HookKiller.server.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static HookKiller.server.common.type.ArticleStatus.DELETE;
import static HookKiller.server.common.type.ArticleStatus.PUBLIC;

@Service
@RequiredArgsConstructor
public class NoticeService {

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
        // TODO : 삭제처리된 게시물도 조회가 가능한것인가?
        NoticeArticle article = noticeArticleRepository.findById(noticeArticleId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
        NoticeContent content = noticeContentRepository.findByNoticeArticleAndLanguage(article, languageType)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
        return NoticeArticleDto.of(article, content);
    }

    /**
     * 리스트 조회
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<NoticeArticleDto> getNoticeList(LanguageType languageType) {
        List<NoticeArticle> articleList = noticeArticleRepository.findAllByStatus(PUBLIC);
        // TODO : NoticeArticle의 정보가 아니라, NoticeArticle이 PUBLIC상태인 게시물의 Content를 파라미터로 받은 languageType의 Content로 리스트를 반환해야 함.
        return noticeArticleRepository.findAll()
                .stream().map(data ->
                        NoticeArticleDto.builder()
                                .id(data.getId())
                                .language(data.getLanguage())
                                .status(data.getStatus())
                                .createdUser(data.getCreatedUser())
                                .updatedUser(data.getUpdatedUser())
                                .build()
                )
                .toList();
    }

    /**
     * 등록
     *
     */
    @Transactional
    public void saveNotice(AddNoticeRequest addNoticeRequest) {
        User loginUser = userUtils.getUser();

        NoticeArticle noticeArticle = noticeArticleRepository.save(
                NoticeArticle.builder()
                        .language(addNoticeRequest.getLanguage())
                        .status(PUBLIC)
                        .createdUser(loginUser)
                        .updatedUser(loginUser)
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
        // TODO : getLanguage에 들어있는 언어에서 다른 언어 번역한 결과 역시 NoticeContent로 제작해서 Save
        noticeContentRepository.saveAll(contentsList);
    }

    /**
     * 수정
     */
    @Transactional
    public void updateNotice(EditNoticeRequest request) {
        //로그인한 사용자 획득
        User user = userUtils.getUser();
        boolean chgTitle = false;
        boolean chgContent = false;

        NoticeArticle article = noticeArticleRepository.findById(request.getNoticeArticleId())
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
        List<NoticeContent> contents = noticeContentRepository.findAllByNoticeArticle(article);

        article.setUpdatedUser(user);
        //다른경우 변경
        if (!request.getLanguage().equals(article.getLanguage()))
            article.setLanguage(request.getLanguage());

        NoticeContent choiceContent = contents.stream()
                .filter(content -> request.getLanguage().equals(content.getLanguage()))
                .findFirst().orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        if(request.getNewTitle() != null && !request.getNewTitle().equals(request.getOrgTitle())) {
            chgTitle = true;
            choiceContent.setTitle(request.getNewTitle());
        }
        if(request.getNewContent() != null && !request.getNewContent().equals(request.getOrgContent())){
            chgContent = true;
            choiceContent.setContent(request.getNewContent());
        }

        if(chgTitle || chgContent) {
            final boolean finalChgTitle = chgTitle;
            final boolean finalChgContent = chgContent;
            contents.stream().filter(content-> choiceContent != content).forEach(content-> {
                if(finalChgTitle){
                    // TODO : request의 언어를 원본으로 해서 content의 언어로 타겟잡아 번역후 title변환
                }
                if(finalChgContent){
                    // TODO : request의 언어를 원본으로 해서 content의 언어로 타겟잡아 번역후 content변환
                }
            });
        }

    }

    /**
     * 삭제
     *
     * @param id
     */
    @Transactional
    public void deleteNotice(Long id) {
        noticeArticleRepository.findById(id).orElseThrow(() ->
                NoticeNotFoundException.EXCEPTION).updateStatus(DELETE);
    }

}
