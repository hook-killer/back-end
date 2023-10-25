package HookKiller.server.auth.helper;

import HookKiller.server.auth.dto.response.OAuthResponse;
import HookKiller.server.jwt.JwtTokenProvider;
import HookKiller.server.user.entity.RefreshTokenEntity;
import HookKiller.server.user.entity.User;
import HookKiller.server.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenGenerateHelper {

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public OAuthResponse execute(User user) {
        String newAccessToken =
                jwtTokenProvider.generateAccessToken(user.getId(), user.getRole().getValue());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        RefreshTokenEntity newRefreshTokenEntity =
                RefreshTokenEntity.builder()
                        .refreshToken(newRefreshToken)
                        .id(user.getId())
                        .ttl(jwtTokenProvider.getRefreshTokenTTLSecond())
                        .build();
        refreshTokenRepository.save(newRefreshTokenEntity);

        return OAuthResponse.builder()
                .userId(user.getId())
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .email(user.getEmail())
                .role(user.getRole().getValue())
                .nickName(user.getNickName())
                .thumbnail(user.getThumbNail())
                .build();
    }
}
