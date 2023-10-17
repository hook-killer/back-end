package HookKiller.server.auth.service;

import HookKiller.server.auth.dto.OIDCUserInfo;
import HookKiller.server.auth.dto.request.AuthRequest;
import HookKiller.server.auth.dto.response.AuthResponse;
import HookKiller.server.auth.dto.response.OAuthResponse;
import HookKiller.server.auth.dto.response.OauthLoginLinkResponse;
import HookKiller.server.auth.dto.response.OauthTokenResponse;
import HookKiller.server.auth.exception.PasswordIncorrectException;
import HookKiller.server.auth.exception.UserNotFoundException;
import HookKiller.server.auth.helper.KakaoOauthHelper;
import HookKiller.server.auth.helper.OIDCHelper;
import HookKiller.server.auth.helper.TokenGenerateHelper;
import HookKiller.server.common.dto.AccessTokenDetail;
import HookKiller.server.common.dto.MailRequest;
import HookKiller.server.common.service.MailHelper;
import HookKiller.server.common.util.SendMailUtil;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.outer.api.oauth.client.KakaoOauthClient;
import HookKiller.server.properties.KakaoOauthProperties;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.exception.AlreadyExistUserException;
import HookKiller.server.user.exception.UserNotActiveException;
import HookKiller.server.user.repository.UserRepository;
import HookKiller.server.user.type.LoginType;
import HookKiller.server.user.type.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final KakaoOauthProperties kakaoOauthProperties;
    private final KakaoOauthHelper kakaoOauthHelper;
    private final TokenGenerateHelper tokenGenerateHelper;
    private final PasswordEncoder passwordEncoder;
    private final MailHelper mailHelper;
    private static final String KAKAO_OAUTH_QUERY_STRING =
            "/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code";

    public ResponseEntity<AuthResponse> loginExecute(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordIncorrectException.EXCEPTION;
        }

        if (user.getStatus().equals(Status.NOT_ACTIVE)) {

            try {
            MailRequest mailRequest = MailRequest.builder()
                    .email(user.getEmail())
                    .build();
            mailHelper.sendVerificationMail(mailRequest);

            } catch (UserNotActiveException e) {
               throw UserNotActiveException.EXCEPTION;
            }
        }

        AuthResponse res = AuthResponse.builder()
                .token(jwtTokenProvider.generateAccessToken(user.getId(), user.getRole().getValue()))
                .build();
        return ResponseEntity.ok(res);
    }

    public boolean loginExecuteTest(String accessToken) {
        log.info("loginExecuteTest 들어옴!");
        AccessTokenDetail accessTokenDetail = jwtTokenProvider.parseAccessToken(accessToken);
        log.info(accessTokenDetail.toString());
        return userRepository.findById(accessTokenDetail.getUserId()).isPresent();
    }

    // 카카오 로그인을 할 수 있게 하는 링크 받기
    public OauthLoginLinkResponse getKakaoOauthLink(String referer) {
        return new OauthLoginLinkResponse(
                kakaoOauthProperties.getKakaoBaseUrl()
                        + String.format(
                        KAKAO_OAUTH_QUERY_STRING,
                        kakaoOauthProperties.getKakaoClientId(),
                        kakaoOauthProperties.getKakaoRedirectUrl()
                ));
    }

    // 카카오 로그인 후 토큰 받기
    public OauthTokenResponse getCredentialFromKaKao(String code, String referer) {
        log.info("referer : {}", referer);
        log.info("referer + 리다이렉트 : {}", referer + "/kakao/callback");
        return OauthTokenResponse.from(
                kakaoOauthHelper.getOauthToken(code, referer)
        );
    }

    // oidc 사용 시 필요없음
//  public OAuthResponse registerUserByKakaoCode(String code) {
//    String accessToken = kakaoOauthHelper.getOauthTokenTest(code).getAccessToken();
//
//    KakaoUserInfoDto userInfo = kakaoOauthHelper.getUserInfo(accessToken);
//
//    User user = userRepository.findByOauthInfo(userInfo.toOauthInfo()).orElseGet(
//            () -> userRepository.save(
//                    User.builder()
//                            .oauthInfo(userInfo.toOauthInfo())
//                            .email(userInfo.getEmail())
//                            .nickName(userInfo.getProfile().getNickname())
//                            .password(UUID.randomUUID().toString())
//                            .role(USER.getValue())
//                            .loginType(KAKAO)
//                            .build()
//            )
//    );
//    return tokenGenerateHelper.execute(user);
//  }

    // 받아온 idToken으로 우리 서비스에 로그인 할 수 있는 accessToken 받아오기
    public OAuthResponse loginUserByIdToken(String idToken) {
        // idToken으로 유저 정보 찾아오기
        OIDCUserInfo oidcUserInfo = kakaoOauthHelper.getOauthInfoByIdToken(idToken);
        User user = userRepository.findByEmail(oidcUserInfo.getEmail()).orElseThrow(() -> UserNotFoundException.EXCEPTION);
        // tokenGenerateHelper에 찾아온 유저 넣어서 execute
        return tokenGenerateHelper.execute(user);
    }
}
