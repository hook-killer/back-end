package HookKiller.server.auth.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {
  
  @NotEmpty(message = "이메일 입력은 필수 입니다.")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
  private String email;
  
  @NotEmpty(message = "닉네임 입력은 필수 입니다.")
  private String nickName;
  
  @NotEmpty(message = "패스워드 입력은 필수 입니다.")
  @Size(min = 3, max = 15)
//  @Pattern(등등 추가)
  private String password;
  
  private String thumbnail;
}
