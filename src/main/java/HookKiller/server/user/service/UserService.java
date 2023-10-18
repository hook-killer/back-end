package HookKiller.server.user.service;

import HookKiller.server.auth.dto.OIDCUserInfo;
import HookKiller.server.auth.dto.request.SingUpRequest;
import HookKiller.server.auth.dto.response.OAuthResponse;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.auth.helper.KakaoOauthHelper;
import HookKiller.server.auth.helper.TokenGenerateHelper;
import HookKiller.server.common.dto.MailRequest;
import HookKiller.server.common.service.MailHelper;
import HookKiller.server.common.util.EmailVerificationUtil;
import HookKiller.server.common.util.TokenGenerator;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.AlreadyExistUserException;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.LoginType;
import HookKiller.server.user.type.Status;
import HookKiller.server.user.type.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import static HookKiller.server.user.type.UserRole.USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final MailHelper mailHelper;
    private final UserRepository userRepository;
    private final KakaoOauthHelper kakaoOauthHelper;
    private final TokenGenerateHelper tokenGenerateHelper;
    private final EmailVerificationUtil emailVerificationUtil;

    @Transactional
    public ResponseEntity<User> registerUser(@RequestBody SingUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw AlreadyExistUserException.EXCEPTION;
        }
        User user =User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickName(request.getNickName())
                .role(USER)
                .loginType(LoginType.DEFAULT)
                .status(Status.NOT_ACTIVE)
                .build();
        user.setVerificationToken(TokenGenerator.generateUniqueToken());
        userRepository.save(user);

        mailHelper.sendVerificationMail(MailRequest.builder().email(user.getEmail()).verificationToken(user.getVerificationToken()).build());
        return ResponseEntity.ok(user);
    }
    
    @Transactional
    public OAuthResponse registerUserByOIDCToken(String idToken) {
        OIDCUserInfo oidcUserInfo = kakaoOauthHelper.getOauthInfoByIdToken(idToken);
        userRepository.findByEmail(oidcUserInfo.getEmail()).ifPresent(user -> {
            throw AlreadyExistUserException.EXCEPTION;
        });
        return tokenGenerateHelper.execute(userRepository.save(User.builder()
                .email(oidcUserInfo.getEmail())
                .password("testtesttest")
                .nickName(oidcUserInfo.getNickName())
                .thumbnail(oidcUserInfo.getThumbnailImg())
                .loginType(LoginType.DEFAULT)
                .role(UserRole.valueOf("USER"))
                .oauthInfo(oidcUserInfo.getOauthInfo())
                .build()));
    }
}
