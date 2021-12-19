package com.oauth2.authservice.dto;

import lombok.Value;

@Value
public class ApiResponse {
    private Boolean success;
    private String message;
}
