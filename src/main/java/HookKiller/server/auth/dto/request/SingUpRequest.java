package HookKiller.server.auth.dto.request;

import HookKiller.server.user.type.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class SingUpRequest {
  
  @NotEmpty(message = "이메일 입력은 필수 입니다.")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
  private String email;
  
  @NotEmpty(message = "닉네임 입력은 필수 입니다.")
  private String nickName;
  
  @NotEmpty(message = "패스워드 입력은 필수 입니다.")
  @Length(min = 8)
//  @Pattern(regexp = " ^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
//          message = "'숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자에서 최대 16자'까지 허용")
  private String password;
  
  private UserRole role;
}
