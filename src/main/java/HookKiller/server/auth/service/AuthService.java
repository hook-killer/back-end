package HookKiller.server.auth.service;

import HookKiller.server.auth.dto.OIDCUserInfo;
import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.dto.response.KakaoUserInfoDto;
import HookKiller.server.auth.dto.response.OAuthResponse;
import HookKiller.server.auth.exception.PasswordIncorrectException;
import HookKiller.server.auth.exception.StatusNotVerificationException;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.auth.helper.OauthHelper;
import HookKiller.server.auth.helper.TokenGenerateHelper;
import HookKiller.server.common.dto.MailRequest;
import HookKiller.server.common.service.MailHelper;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.outer.api.oauth.client.GoogleTokenInfo;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.LoginType;
import HookKiller.server.user.type.Status;
import HookKiller.server.user.type.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static HookKiller.server.common.util.SecurityUtils.passwordEncoder;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final OauthHelper oauthHelper;
    private final TokenGenerateHelper tokenGenerateHelper;
    private final MailHelper mailHelper;
    private final GoogleTokenInfo googleTokenInfo;

    public ResponseEntity<AuthResponse> loginExecute(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordIncorrectException.EXCEPTION;
        }

        if (user.getStatus().equals(Status.NOT_ACTIVE)) {
            mailHelper.sendVerificationMail(MailRequest.builder().email(user.getEmail()).verificationToken(user.getVerificationToken()).build());
            throw StatusNotVerificationException.EXCEPTION;
        }

        AuthResponse res = AuthResponse.builder()
                .token(jwtTokenProvider.generateAccessToken(user.getId(), user.getRole().getValue()))
                .role(user.getRole().getValue())
                .nickName(user.getNickName())
                .thumbNail(user.getThumbNail())
                .build();

        return ResponseEntity.ok(res);
    }

    public OAuthResponse registerUserKakaoCode(String code) {
        String accessToken = oauthHelper.getOauthTokenKakao(code).getAccessToken();
        log.info("AceessToken >>> {}", accessToken);
        KakaoUserInfoDto userInfo = oauthHelper.getKakaoUserInfo(accessToken);

        User user =
                userRepository.findByEmail(userInfo.getEmail())
                        .orElseGet(() ->
                                userRepository.findByEmail(userInfo.getEmail())
                                        .orElse(userRepository.save(User.builder()
                                                .email(userInfo.getEmail())
                                                .password(UUID.randomUUID().toString())
                                                .nickName(userInfo.getUserName())
                                                .thumbNail(userInfo.getProfileImage())
                                                .loginType(LoginType.KAKAO)
                                                .role(UserRole.USER)
                                                .status(Status.ACTIVE)
                                                .oauthInfo(userInfo.toOauthInfo())
                                                .build())));


        return tokenGenerateHelper.execute(user);
    }

    public OAuthResponse registerGoogleUser(String code) {
        String accessToken = oauthHelper.getOauthTokenGoogle(code).getAccessToken();
        OIDCUserInfo userInfo = oauthHelper.getGoogleInfoById(googleTokenInfo.googleUserInfo(accessToken));

        User user =
                userRepository.findByEmail(userInfo.getEmail())
                        .orElseGet(() ->
                                userRepository.findByEmail(userInfo.getEmail())
                                        .orElse(userRepository.save(User.builder()
                                                .email(userInfo.getEmail())
                                                .password(UUID.randomUUID().toString())
                                                .nickName(userInfo.getName())
                                                .thumbNail(userInfo.getPicture())
                                                .loginType(LoginType.GOOGLE)
                                                .role(UserRole.USER)
                                                .status(Status.ACTIVE)
                                                .oauthInfo(userInfo.getOauthInfo())
                                                .build())));
        return tokenGenerateHelper.execute(user);
    }
}
