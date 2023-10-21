package HookKiller.server.board.controller;

import HookKiller.server.board.dto.PostReplyRequestDto;
import HookKiller.server.board.dto.ReplyResponseDto;
import HookKiller.server.board.service.ReplyService;
import HookKiller.server.common.type.LanguageType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

  private final ReplyService replyService;

  @PostMapping
  public void createReply(@RequestBody PostReplyRequestDto requestDto) {
    replyService.createReply(requestDto);
  }

  @GetMapping("/{articleId}")
  public List<ReplyResponseDto> getReplyList(@PathVariable Long articleId, HttpServletRequest request) {
    return replyService.getReplyList(articleId, LanguageType.findTypeByRequest(request));
  }

  @DeleteMapping("/{replyId}")
  public ResponseEntity<String> deleteReply(@PathVariable Long replyId) {
    replyService.deleteReply(replyId);
    return ResponseEntity.ok("삭제처리가 완료되었습니다.");
  }

}
