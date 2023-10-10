package HookKiller.server.notice.controller;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.notice.dto.NoticeArticleDto;
import HookKiller.server.notice.dto.NoticeContentDto;
import HookKiller.server.notice.entity.NoticeArticle;
import HookKiller.server.notice.entity.NoticeContent;
import HookKiller.server.repository.NoticeArticleRepository;
import HookKiller.server.repository.NoticeContentRepository;
import HookKiller.server.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final NoticeArticleRepository articleRepository;
    private final NoticeContentRepository contentRepository;

    /**
     *  단건 조회
     * @param noticeArticleId
     * @param request
     * @param languageType
     * @return
     */
    @GetMapping("/{noticeArticleId}")
    public NoticeArticleDto getNoticeArticle(@PathVariable Long noticeArticleId, HttpServletRequest request, LanguageType languageType) {
        request.getHeader("languageType");
        return noticeService.getNoticeArticleByArticleId(noticeArticleId, languageType);
    }

    /**
     * 리스트 조회
     * @param request
     * @param languageType
     * @return
     */
    @GetMapping("/list")
    public List<NoticeArticleDto> getNoticeArticleList(HttpServletRequest request, LanguageType languageType) {

        return noticeService.getNoticeList(languageType);
    }

    /**
     * 공지사항 등록
     */
    @PostMapping
    public void addNotice(@RequestBody NoticeArticleDto articleRequest, @Valid NoticeContentDto contentRequest) {
        noticeService.saveNotice(articleRequest, contentRequest);
    }

    /**
     * 공지사항 수정
     */
    @PutMapping
    public void updateNotice(@RequestBody NoticeArticleDto articleDto, @Valid NoticeContentDto contentDto, Long id) {
        noticeService.update(id, contentDto);
        Optional<NoticeContent> findNotice = contentRepository.findById(id);
    }

    /**
     * 공지사항 삭제
     */
    @DeleteMapping("/{noticeArticleId}")
    public void deleteNotice(@PathVariable Long noticeArticleId) {
        noticeService.deleteNotice(noticeArticleId);
    }

}

