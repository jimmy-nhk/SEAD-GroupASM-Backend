package com.sead.Crud.CrudService.service;

import com.sead.Crud.CrudService.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    private final String userUrl = "http://localhost:8080/api/";

    public UserDTO getUserById(Long userId){
        HttpHeaders headers = new HttpHeaders();
        String finalToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjQwODM0MTMwLCJleHAiOjE2NDE2OTgxMzB9._ZvUfL9KuhZj_HNgUNk20zbmyV5dm4kk-a9yOQkbHtVCoywoEtREswKmHi1JZ5HyfDLHvC0jE-Q4RUUs8jvNNw";
        headers.setBearerAuth(finalToken);

        HttpEntity<UserDTO> response = restTemplate.exchange(userUrl+"user/get/id="+userId, HttpMethod.GET, new HttpEntity<String>(headers),  UserDTO.class);
        return response.getBody();
    }

}
