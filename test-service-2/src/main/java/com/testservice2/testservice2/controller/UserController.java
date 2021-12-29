package com.testservice2.testservice2.controller;

import com.testservice2.testservice2.VO.ResponseTemplate;
import com.testservice2.testservice2.entity.User;
import com.testservice2.testservice2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("In saveUser of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/p/{id}")
    public ResponseTemplate getUserWithProvider(@PathVariable("id") long id){
        log.info("In getUserWithProvider of UserController");
        return userService.getUser(id);
    }
}
