package com.metacraft.assetstore.Entities.Form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserForm {

  @Size(min = 5, max = 20, message = "The ID must be between 5 and 20 characters.")
  @NotEmpty(message = "The ID is necessary.")
  private String username;

  @NotEmpty(message = "The email is neseccary.")
  @Email
  private String email;

  @NotEmpty(message = "The password is necessary.")
  private String password1;

  @NotEmpty(message = "You must confirm your password.")
  private String password2;

}
