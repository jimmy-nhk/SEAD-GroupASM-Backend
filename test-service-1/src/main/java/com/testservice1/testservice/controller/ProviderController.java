package com.testservice1.testservice.controller;

import com.testservice1.testservice.entity.Provider;
import com.testservice1.testservice.service.Test_Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test_service_1")
@Slf4j
public class Test_Controller_1 {
    @Autowired
    private Test_Service test_service;

    @PostMapping("/")
    public Provider saveTest(@RequestBody Provider testClass){
        log.info("In saveTest of test_service 1");
        return test_service.saveTest(testClass);
    }

    @GetMapping("/{id}")
    public Provider getTest(@PathVariable("id") long id){
        log.info("In getTest of test_service 1");
        return test_service.getId(id);
    }
}
