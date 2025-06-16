package com.product.trial.master.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "username is mandatory")
    private String username;
    @NotBlank(message = "firstname is mandatory")
    private String firstname;
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotBlank(message = "email is mandatory")
    @Email
    private String email;

}
