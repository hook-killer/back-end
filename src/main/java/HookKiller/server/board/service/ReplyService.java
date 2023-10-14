package HookKiller.server.board.service;

import HookKiller.server.board.dto.ReplyRequestDto;
import HookKiller.server.board.entity.Article;
import HookKiller.server.board.entity.Reply;
import HookKiller.server.board.entity.ReplyContent;
import HookKiller.server.board.exception.ArticleContentNotFoundException;
import HookKiller.server.board.exception.ReplyContentNotFoundException;
import HookKiller.server.board.repository.ArticleRepository;
import HookKiller.server.board.repository.ReplyContentRepository;
import HookKiller.server.board.repository.ReplyRepository;
import HookKiller.server.board.type.ReplyStatus;
import HookKiller.server.common.service.TranslateService;
import HookKiller.server.common.type.LanguageType;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {

  private final UserUtils userUtils;
  private final ArticleRepository articleRepository;
  private final ReplyRepository replyRepository;
  private final ReplyContentRepository replyContentRepository;
  private final TranslateService translateService;

  @Transactional
  public void createReply(ReplyRequestDto requestDto) {

    User user = userUtils.getUser();

    Reply reply = replyRepository.save(
            Reply.builder()
                    .isDeleted(ReplyStatus.FALSE)
                    .orgReplyLanguage(requestDto.getOrgReplyLanguage())
                    .createdUser(user)
                    .updatedUser(user)
                    .build()
    );

    List<ReplyContent> replyContentList = new ArrayList<>();
    replyContentList.add(
            ReplyContent.builder()
                    .reply(reply)
                    .language(requestDto.getOrgReplyLanguage())
                    .content(requestDto.getContent())
                    .build()
    );

    requestDto
            .getOrgReplyLanguage()
            .getTranslateTargetLanguage()
            .forEach(targetLanguage ->
                    replyContentList.add(
                            ReplyContent
                                    .builder()
                                    .reply(reply)
                                    .language(targetLanguage)
                                    .content(
                                            translateService.naverPapagoTextTranslate(
                                                    requestDto.getOrgReplyLanguage(), targetLanguage, requestDto.getContent()
                                            )
                                    )
                                    .build()
                    )
            );

    replyContentRepository.saveAll(replyContentList);
  }


  @Transactional(readOnly = true)
  public List<ReplyRequestDto> getReplyList(Long articleId, LanguageType language) {
    Article article = articleRepository.findById(articleId).orElseThrow(() -> ArticleContentNotFoundException.EXCEPTION);
    List<Reply> replyList = replyRepository.findAllByArticleAndDeleted(article, false);

    return replyList.stream()
            .map(reply ->
                    ReplyRequestDto.of(reply, replyContentRepository
                            .findByReplyAndLanguage(reply, language)
                            .orElseThrow(() -> ReplyContentNotFoundException.EXCEPTION)))
            .toList();
  }

  @Transactional
  public void deleteReply(Long replyId) {
    replyRepository
            .findById(replyId)
            .orElseThrow(() -> ReplyContentNotFoundException.EXCEPTION)
            .updateStatus(ReplyStatus.TURE);
  }

}
