package com.ms.core.user.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class UpdateUserDto {

  @NotEmpty(message = "*Please provide user userId")
  private String userId;

  @NotEmpty(message = "*Please provide user email")
  private String email;

  @Length(min = 6, message = "*Your password must have at least 6 characters")
  @Pattern(
      regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$",
      message = "*Your password is not strong enough!")
  @NotEmpty(message = "*Please provide user password")
  private String password;

  @NotEmpty(message = "*Please provide user first name")
  private String firstName;

  @NotEmpty(message = "*Please provide user contact number")
  private String contactNumber;

  @NotEmpty(message = "*Please provide user last name")
  private String lastName;

  private boolean active;
}
