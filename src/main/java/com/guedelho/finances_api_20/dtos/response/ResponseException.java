package com.guedelho.finances_api_20.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data

public class ResponseException {
    public int statusCode;
    public String status;
    public List<String> message;

    public ResponseException(int statusCode, String status, List<String> message) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
    }
}