package HookKiller.server.auth.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails {
    
    private final String userId;
    private final String role;

    // 해당 유저의 권한을 리턴하는 메서드!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> this.role);
        return collection;
    }

    @Override
    public String getPassword() {
        // TODO : getPassword는 나중에 쓴다면 다시 작성
        return this.userId;
    }

    @Override
    public String getUsername() {
        return String.valueOf(this.userId);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public static CustomUserDetails of(String userId, String role) {
        return CustomUserDetails.builder()
                .userId(userId)
                .role(role)
                .build();
    }

}
