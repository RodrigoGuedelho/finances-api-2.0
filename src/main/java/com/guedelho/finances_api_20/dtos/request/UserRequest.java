package com.guedelho.finances_api_20.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    @Email(message = "Email invalido.")
    private String login;
    @NotBlank(message = "Email não informado.")
    private String name;
    @NotBlank(message = "Password não informado.")
    private String password;
}
