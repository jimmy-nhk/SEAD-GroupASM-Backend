package com.testservice1.testservice.service;

import com.testservice1.testservice.entity.Provider;
import com.testservice1.testservice.repository.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Test_Service {
    @Autowired
    private ProviderRepository provider_repository;

    public Provider saveTest(Provider testClass) {
        log.info("in saveTest of save service 1");
        return provider_repository.save(testClass);
    }

    public Provider getId(long id) {
        log.info("in getId of save service 1");
        return provider_repository.findByClassTest_Class_1_id(id);
    }
}
