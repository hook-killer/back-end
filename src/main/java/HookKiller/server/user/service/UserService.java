package HookKiller.server.user.service;

import HookKiller.server.user.dto.request.UserLoginDto;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signUp(UserLoginDto userLoginDto) throws Exception {
        if (userRepository.findByEmail(userLoginDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }
    }
}
