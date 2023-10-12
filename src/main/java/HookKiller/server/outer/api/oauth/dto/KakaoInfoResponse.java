package HookKiller.server.outer.api.oauth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoInfoResponse {

    private String id;
    private KakaoAccount kakaoAccount;
    private Properties properties;

    @Getter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Properties {
        private String nickname;
        private String thumbnailImage;
        private String profileImage;
        private String customField1;
        private String customField2;
    }

    @Getter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class KakaoAccount {

        private boolean profileNeedsAgreement;
        private boolean emailNeedsAgreement;
        private boolean isEmailValid;
        private boolean isEmailVerified;
        private Profile profile;
        private String email;
        private boolean nameNeedsAgreement;
        private String name;
        private boolean ageRangeNeedsAgreement;
        private String ageRange;
        private boolean birthdayNeedsAgreement;
        private String birthday;
        private boolean genderNeedsAgreement;
        private String gender;


        @Getter
        @NoArgsConstructor
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public static class Profile {
            private String nickname;
            private String thumbnailImageUrl;
            private String profileImageUrl;
            private boolean isDefaultImage;
        }

        public String getNickName() {
            return profile.getNickname();
        }
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return kakaoAccount.getEmail();
    }

    public String getName() {
        return kakaoAccount.getName() != null ? kakaoAccount.getName() : properties.getNickname();
    }

    public String getNickName() {
        return kakaoAccount.getNickName();
    }
}

