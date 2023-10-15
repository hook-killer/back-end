package HookKiller.server.admin.service;

import HookKiller.server.auth.dto.request.RegisterRequest;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.dto.UserDto;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.AlreadyExistUserException;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.Status;
import HookKiller.server.user.type.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static HookKiller.server.user.type.UserRole.ADMIN;
import static HookKiller.server.user.type.UserRole.USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserUtils userUtils;

    /**
     * 관리자 계정 등록
     */
    @Transactional
    public void regAdmin(RegisterRequest registerRequest) {

        User requestAdmin = userUtils.verificationRequestUserAdminAndGetUser();

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw AlreadyExistUserException.EXCEPTION;
        }

        User user = userRepository.save(
                User.builder()
                        .email(registerRequest.getEmail())
                        .nickName(registerRequest.getNickName())
                        .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .role(ADMIN)
                        .build()
        );
        log.info("관리자 계정 등록 : 계정생성자 >>> {}, 생성계정 >>> {}", requestAdmin.getEmail(), user.getEmail());
    }

    /**
     * 계정 리스트 조회
     */
    @Transactional(readOnly = true)
    public List<UserDto> acctListByRole(UserRole role) {
        userUtils.verificationiRequestUserAdmin();

        // 계정을 role로 구분
        List<User> accounts = (role == ADMIN) ?
                userRepository.findAllByRole(ADMIN) :
                userRepository.findAllByRole(USER);

        log.info("계정 리스트 >>> {}", role);

        return accounts.stream()
                .map(account -> UserDto.builder()
                        .id(account.getId())
                        .email(account.getEmail())
                        .nickName(account.getNickName())
                        .createdAt(account.getCreateAt())
                        .build())
                .toList();
    }

    /**
     * 계정 상태 변경
     */
    @Transactional
    public void modifyAcctStat(Long id, Status status) {

        userUtils.verificationiRequestUserAdmin();
        log.info("계정 {} >>> {}", status, id);

        userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION)
                .updateUserStatus(status);
    }

}
