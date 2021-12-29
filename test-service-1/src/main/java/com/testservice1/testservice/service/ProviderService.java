package com.testservice1.testservice.service;

import com.testservice1.testservice.entity.Provider;
import com.testservice1.testservice.repository.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProviderService {
    @Autowired
    private ProviderRepository provider_repository;

    public Provider saveProvider(Provider provider) {
        log.info("in saveTest of provider service");
        return provider_repository.save(provider);
    }

    public Provider getId(long id) {
        log.info("in getId of provider service");
        return provider_repository.findByProviderId(id);
    }
}
