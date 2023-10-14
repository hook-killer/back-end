package HookKiller.server.admin.service;

import HookKiller.server.admin.exception.UserNotAdminException;
import HookKiller.server.auth.dto.request.RegisterRequest;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.dto.UserDto;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.AlreadyExistUserException;
import HookKiller.server.user.repository.UserRepository;
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

        log.info("관리자 계정 등록 service");

        authorityVerification();

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
    }

    /**
     * 관리자 계정 리스트
     */
    @Transactional(readOnly = true)
    public List<UserDto> adminList() {

        log.info("관리자 계정 리스트");

        authorityVerification();

        return userRepository.findAllByRole(ADMIN)
                .stream()
                .map(user -> {
                    return UserDto.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .nickName(user.getNickName())
                            .build();
                }).toList();
    }

    /**
     * 사용자 계정 리스트
     */
    @Transactional(readOnly = true)
    public List<UserDto> userList() {

        log.info("사용자 계정 리스트");

        authorityVerification();

        return userRepository.findAllByRole(USER)
                .stream()
                .map(user -> {
                    return UserDto.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .nickName(user.getNickName())
                            .build();
                }).toList();
    }

    /**
     * 계정 상태 변경
     */
    @Transactional
    public void deleteAdmin() {

        log.info("계정 상태 변경");

        authorityVerification();

    }

    private void authorityVerification() {
        if (!userUtils.getUser().getRole().equals(ADMIN)) {
            throw UserNotAdminException.EXCEPTION;
        }
    }

}
