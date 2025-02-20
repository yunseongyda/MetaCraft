package com.metacraft.assetstore.Entities.Form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserForm {

  @Size(min = 5, max = 20)
  @NotEmpty(message = "사용자 Id는 필수 항목입니다.")
  private String username;

  @NotEmpty(message = "이메일은 필수 항목입니다.")
  @Email
  private String email;

  @NotEmpty(message = "비밀번호는 필수 항목입니다.")
  private String password1;

  @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
  private String password2;

}
