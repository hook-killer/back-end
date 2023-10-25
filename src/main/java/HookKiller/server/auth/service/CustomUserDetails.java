package HookKiller.server.auth.service;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Builder
public record CustomUserDetails(String userId, String role, String nickname) implements UserDetails {
    
    // 해당 유저의 권한을 리턴하는 메서드!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> this.role);
        return collection;
    }
    
    @Override
    public String getPassword() {
        return null;
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
