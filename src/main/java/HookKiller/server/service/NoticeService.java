package HookKiller.server.service;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.notice.dto.NoticeArticleDto;
import HookKiller.server.notice.dto.NoticeContentDto;
import HookKiller.server.notice.entity.NoticeArticle;
import HookKiller.server.notice.entity.NoticeContent;
import HookKiller.server.notice.exception.NoticeArticleNotFoundException;
import HookKiller.server.repository.NoticeArticleRepository;
import HookKiller.server.repository.NoticeContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeArticleRepository noticeArticleRepository;
    private final NoticeContentRepository noticeContentRepository;

    @Transactional
    public void saveNotice(NoticeArticleDto articleDto, NoticeContentDto contentDto) {
        NoticeArticle noticeArticle = NoticeArticle.builder()
                .id(articleDto.getId())
                .build();
        NoticeContent noticeContent = NoticeContent.builder()
                        .id(contentDto.getId())
                                .build();
        noticeArticleRepository.save(noticeArticle);
        noticeContentRepository.save(noticeContent);
    }

    @Transactional(readOnly = true)
    public List<NoticeArticleDto> getNoticeList() {

        List<NoticeArticle> all = noticeArticleRepository.findAll();
        List<NoticeArticleDto> noticeArticleDtoList = new ArrayList<>();

        for (NoticeArticle noticeArticle : all) {
            NoticeArticleDto articleDto = NoticeArticleDto.builder()
                    .id(noticeArticle.getId())
                    .language(noticeArticle.getLanguage())
                    .status(noticeArticle.getStatus())
                    .createdAt(noticeArticle.getCreatedAt())
                    .createdUser(noticeArticle.getCreatedUser())
                    .updatedAt(noticeArticle.getUpdatedAt())
                    .updatedUser(noticeArticle.getUpdatedUser())
                    .build();
            noticeArticleDtoList.add(articleDto);
        }
        return noticeArticleDtoList;
    }

    @Transactional
    public NoticeContentDto getNotice(Long id) {
        NoticeContent noticeContent = noticeContentRepository.findById(id).orElseThrow(() -> NoticeArticleNotFoundException.EXCEPTION);

        return NoticeContentDto.builder()
                .id(noticeContent.getId())
                .language(noticeContent.getLanguage())
                .title(noticeContent.getTitle())
                .content(noticeContent.getContent())
                .build();
    }

    @Transactional
    public void deleteNotice(Long id) {
        noticeArticleRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, NoticeArticleDto articleDto) {
        Optional<NoticeArticle> byId = noticeArticleRepository.findById(id);
        // TODO : 수정 받고 다시 작업 예정
    }

    public NoticeArticleDto getNoticeArticleByArticleId(Long noticeArticleId, LanguageType languageType) {
        return null;
    }

    public List<NoticeArticleDto> getNoticeArticleList(Long noticeArticleId, LanguageType languageType) {
        return null;
    }

}
