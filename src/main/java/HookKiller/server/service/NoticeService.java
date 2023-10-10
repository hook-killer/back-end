package HookKiller.server.service;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.notice.dto.NoticeArticleDto;
import HookKiller.server.notice.dto.NoticeContentDto;
import HookKiller.server.notice.entity.NoticeArticle;
import HookKiller.server.notice.entity.NoticeContent;
import HookKiller.server.notice.exception.NoticeArticleNotFoundException;
import HookKiller.server.notice.exception.NoticeContentNotFoundException;
import HookKiller.server.repository.NoticeArticleRepository;
import HookKiller.server.repository.NoticeContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeArticleRepository noticeArticleRepository;
    private final NoticeContentRepository noticeContentRepository;

    /**
     * 단건 조회
     *
     * @param noticeArticleId
     * @param languageType
     * @return
     */
    public NoticeArticleDto getNoticeArticleByArticleId(Long noticeArticleId, LanguageType languageType) {
        NoticeArticle article = noticeArticleRepository.findById(noticeArticleId)
                .orElseThrow(() -> NoticeArticleNotFoundException.EXCEPTION);
        NoticeContent content = noticeContentRepository.findByNoticeArticleAndLanguage(article, languageType)
                .orElseThrow(() -> NoticeContentNotFoundException.EXCEPTION);
        return NoticeArticleDto.of(article, content);
    }

    /**
     * 리스트 조회
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<NoticeArticleDto> getNoticeList(LanguageType languageType) {
        return noticeArticleRepository.findAll()
                .stream().map(data ->
                        NoticeArticleDto.builder()
                                .id(data.getId())
                                .language(data.getLanguage())
                                .status(data.getStatus())
                                .createdUser(data.getCreatedUser())
                                .updatedUser(data.getUpdatedUser())
                                .build())
                .toList();
    }

    /**
     * 등록
     *
     * @param articleDto
     * @param contentDto
     */
    @Transactional
    public void saveNotice(NoticeArticleDto articleDto, NoticeContentDto contentDto) {
        NoticeArticle noticeArticle = NoticeArticle.builder()
                .id(articleDto.getId())
                .language(articleDto.getLanguage())
                .noticeArticleStatus(articleDto.getStatus())
                .createdUser(articleDto.getCreatedUser())
                .updatedUser(articleDto.getUpdatedUser())
                .build();
        NoticeContent noticeContent = NoticeContent.builder()
                .id(contentDto.getId())
                .language((contentDto.getLanguage()))
                .title(contentDto.getTitle())
                .content(contentDto.getContent())
                .build();
        noticeArticleRepository.save(noticeArticle);
        noticeContentRepository.save(noticeContent);
    }

    /**
     * 수정
     *
     * @param id
     * @param contentDto
     */
    @Transactional
    public void update(Long id, NoticeContentDto contentDto) {
        Optional<NoticeContent> byId = noticeContentRepository.findById(id);
        // TODO : 수정 받고 다시 작업 예정
    }

    /**
     * 삭제
     *
     * @param id
     */
    @Transactional
    public void deleteNotice(Long id) {
        noticeArticleRepository.deleteById(id);
    }

}
