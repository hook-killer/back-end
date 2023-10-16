package HookKiller.server.user.service;

import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.board.dto.ArticleRequestDto;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.dto.MyPageRequestDto;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserUtils userUtils;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public MyPageRequestDto getMyPage(Long userId) {
        // 로그인한 사용자 정보를 가져옴
        User user = userUtils.getUser();

        User requestUser = userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (user.getId().equals(requestUser.getId())) {
            return MyPageRequestDto.from(requestUser);
        }

        return null;
    }

    @Transactional
    public void updatePasswordAndNickname(MyPageRequestDto myPageRequestDto) {
        User user = userUtils.getUser();

        User requestUser = userRepository.findById(myPageRequestDto.getUserId())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        if (myPageRequestDto.getPassword() != null) {
            requestUser.setPassword(myPageRequestDto.getPassword());
        }
        if (myPageRequestDto.getNickName() != null) {
            requestUser.setNickName(myPageRequestDto.getNickName());
        }

        userRepository.save(requestUser);

    }

    @Transactional(readOnly = true)
    public List<ArticleRequestDto> getMyList(Long userId) {
        return null;
    }



}
