package HookKiller.server.notice.controller;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.type.RequestHeaderConstants;
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
import org.apache.commons.codec.language.bm.Lang;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.springframework.ui.Model;
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
     * @return
     */
    @GetMapping("/{noticeArticleId}")
    public NoticeArticleDto getNoticeArticle(@PathVariable Long noticeArticleId, HttpServletRequest request) {
        LanguageType.findTypeByRequest(null);
        return noticeService.getNoticeArticleByArticleId(noticeArticleId, LanguageType.findTypeByRequest(request));
    }

    /**
     * 리스트 조회
     * @param request
     * @return
     */
    @GetMapping
    public List<NoticeArticleDto> getNoticeArticleList(HttpServletRequest request) {
        return noticeService.getNoticeList(LanguageType.findTypeByRequest(request));
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

