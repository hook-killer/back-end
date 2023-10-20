package HookKiller.server.auth.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
public class AuthRequest implements Serializable {
  private static final long serialVersionUID = 1L;


  @NotEmpty(message = "이메일 입력은 필수 입니다.")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
  private String email;

  @NotEmpty(message = "패스워드 입력은 필수 입니다.")
  @Length(min = 8)
//  @Pattern(등등 추가)
  private String password;

  @Builder
  public AuthRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
