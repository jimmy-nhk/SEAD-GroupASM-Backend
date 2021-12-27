package com.testservice1.testservice.controller;

import com.testservice1.testservice.entity.Provider;
import com.testservice1.testservice.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provider")
@Slf4j
public class ProviderController {
    @Autowired
    private ProviderService provider_service;

    @PostMapping("/")
    public Provider saveProvider(@RequestBody Provider provider){
        log.info("In saveProvider of provider controller");
        return provider_service.saveProvider(provider);
    }

    @GetMapping("/{id}")
    public Provider getTest(@PathVariable("id") long id){
        log.info("In getTest of provider controller");
        return provider_service.getId(id);
    }
}
