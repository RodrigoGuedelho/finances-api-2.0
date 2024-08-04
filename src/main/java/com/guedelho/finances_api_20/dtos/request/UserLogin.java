package com.guedelho.finances_api_20.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record UserLogin(@NotBlank(message = "Login não informado") String login, @NotBlank(message = "Password não informado.") String password) {
}
