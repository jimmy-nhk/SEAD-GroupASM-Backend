package com.oauth2.authservice.controller;

import com.oauth2.authservice.config.CurrentUser;
import com.oauth2.authservice.dto.LocalUser;
import com.oauth2.authservice.dto.UserInfo;
import com.oauth2.authservice.model.User;
import com.oauth2.authservice.service.UserServiceImpl;
import com.oauth2.authservice.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser user) {
        return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getContent() {
        return ResponseEntity.ok("Public content goes here");
    }

//    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> getUserContent() {
//        return ResponseEntity.ok("User content goes here");
//    }
//
//    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> getAdminContent() {
//        return ResponseEntity.ok("Admin content goes here");
//    }
//
//    @GetMapping("/mod")
//    @PreAuthorize("hasRole('MODERATOR')")
//    public ResponseEntity<?> getModeratorContent() {
//        return ResponseEntity.ok("Moderator content goes here");
//    }

    @GetMapping("/user/get/id={id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserInfo getUserById(@PathVariable long id){
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            List<String> roles = user.getRoles().stream().map(item -> item.toString()).collect(Collectors.toList());
            return UserInfo.builder()
                    .displayName(user.getDisplayName())
                    .email(user.getEmail())
                    .id(user.getId().toString())
                    .imageUrl(user.getImageUrl())
                    .roles(roles)
                    .build();
        } else {
            return null;
        }
    }

    @PutMapping("/user/update")
    @PreAuthorize("hasRole('ADMIN')")
    public UserInfo updateUser(@RequestBody UserInfo userInfo){
        Optional<User> optionalUser = userService.findUserById(Long.valueOf(userInfo.getId()));
        System.out.println(userInfo);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setDisplayName(userInfo.getDisplayName());
            user.setImageUrl(userInfo.getImageUrl());
            userService.updateUser(user);
            System.out.println("User updated");
        } else{
            System.out.println("Cannot find user");
        }
        return userInfo;
    }
}