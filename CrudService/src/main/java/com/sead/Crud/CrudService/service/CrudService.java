package com.sead.Crud.CrudService.service;

import com.sead.Crud.CrudService.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CrudService {
    @Autowired
    private RestTemplate restTemplate;

    public PostDTO getPostById(Long postId){
        PostDTO postDTO = restTemplate.getForObject("http://localhost:8082/post/get/id=" + postId, PostDTO.class);
        System.out.println(postDTO.toString());
        return postDTO;
    }


}
