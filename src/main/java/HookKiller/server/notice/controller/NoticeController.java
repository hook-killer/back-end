package HookKiller.server.notice.controller;

import HookKiller.server.common.type.LanguageType;
import HookKiller.server.notice.dto.AddNoticeRequest;
import HookKiller.server.notice.dto.EditNoticeRequest;
import HookKiller.server.notice.dto.NoticeArticleDto;
import HookKiller.server.notice.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 단건 조회
     *
     * @param noticeArticleId
     * @param request
     * @return
     */
    @GetMapping("/{noticeArticleId}")
    public NoticeArticleDto getNoticeArticle(@PathVariable Long noticeArticleId, HttpServletRequest request) {
        log.info("공지사항 단건 조회 >>> {}", noticeArticleId);
        return noticeService.getNoticeArticleByArticleId(noticeArticleId, LanguageType.findTypeByRequest(request));
    }

    /**
     * 리스트 조회
     *
     * @param request
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getNoticeArticleList(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int articleLimit,
            HttpServletRequest request
    ) {
        log.info("공지사항 리스트 조회");
        return ResponseEntity.ok(noticeService.getNoticeList(page, articleLimit, LanguageType.findTypeByRequest(request)));
    }

    /**
     * 공지사항 등록
     */
    @PostMapping
    public void addNotice(@RequestBody @Valid AddNoticeRequest request) {
        log.info("공지사항 등록");
        noticeService.saveNotice(request);
    }

    /**
     * 공지사항 수정
     */
    @PutMapping
    public void updateNotice(@RequestBody @Valid EditNoticeRequest request) {
        log.info("공지사항 수정");
        noticeService.updateNotice(request);
    }

    /**
     * 공지사항 삭제
     */
    @DeleteMapping("/{noticeArticleId}")
    public void deleteNotice(@PathVariable Long noticeArticleId) {
        noticeService.deleteNotice(noticeArticleId);
    }

}

