package com.oauth2.authservice.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UserInfo {
    private String id, displayName, email;
    private List<String> roles;
    private String imageUrl;
}
