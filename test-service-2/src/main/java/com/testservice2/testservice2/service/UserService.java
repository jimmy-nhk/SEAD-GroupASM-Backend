package com.testservice2.testservice2.service;

import com.netflix.discovery.converters.Auto;
import com.testservice2.testservice2.VO.Provider;
import com.testservice2.testservice2.VO.ResponseTemplate;
import com.testservice2.testservice2.entity.User;
import com.testservice2.testservice2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("In saveUser of User service");
        return userRepository.save(user);
    }

    public ResponseTemplate getUser(long id) {
        log.info("In getUser of UserService");
        ResponseTemplate response = new ResponseTemplate();
        User user = userRepository.getByUserId(id);
        Provider provider = restTemplate.getForObject("http://localhost:9051/provider/" + user.getProviderId(), Provider.class);
        response.setUser(user);
        response.setProvider(provider);
        return response;
    }
}
