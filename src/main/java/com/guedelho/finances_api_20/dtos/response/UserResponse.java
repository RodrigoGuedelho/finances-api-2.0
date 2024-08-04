package com.guedelho.finances_api_20.dtos.response;

import com.guedelho.finances_api_20.enums.GenericStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String login;
    private String name;
    private GenericStatus status;
}
