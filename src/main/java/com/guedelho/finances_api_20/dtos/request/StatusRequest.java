package com.guedelho.finances_api_20.dtos.request;

import com.guedelho.finances_api_20.enums.GenericStatus;
import lombok.Data;

@Data
public class StatusRequest {
    private GenericStatus status;
}
