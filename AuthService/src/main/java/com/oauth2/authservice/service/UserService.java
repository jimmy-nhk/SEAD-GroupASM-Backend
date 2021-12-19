package com.oauth2.authservice.service;

import com.oauth2.authservice.dto.LocalUser;
import com.oauth2.authservice.dto.SignUpRequest;
import com.oauth2.authservice.exception.UserAlreadyExistAuthenticationException;
import com.oauth2.authservice.model.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

    User findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
}
