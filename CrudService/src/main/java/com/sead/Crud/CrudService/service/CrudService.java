package com.sead.Crud.CrudService.service;

import com.sead.Crud.CrudService.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CrudService {
    @Autowired
    private RestTemplate restTemplate;

    private final String postUrl = "http://localhost:8082/post/";

    public PostDTO getPostById(Long postId){
        PostDTO postDTO = restTemplate.getForObject(postUrl+"get/id=" + postId, PostDTO.class);
        System.out.println(postDTO.toString());
        return postDTO;
    }

    public PostDTO createPost(PostDTO post){
        PostDTO postDTO = restTemplate.postForObject(postUrl+"create" , post, PostDTO.class);
        System.out.println(postDTO.toString());
        return postDTO;

    }

    // update post
    public String updatePost(PostDTO post){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PostDTO> requestUpdate = new HttpEntity<>(post, headers);

        try{
            restTemplate.exchange(postUrl+"update" , HttpMethod.PUT, requestUpdate, PostDTO.class );

            return "Successfully update the post";
        }catch (Exception e){
            return "Fail to update";
        }
    }


    // delete post
    public String deletePost(Long postId) {

        try {
            restTemplate.delete(postUrl+"delete/id=" + postId);
            //TODO: delete all the comments in the post

            return "Successfully delete the post";
        }catch (Exception e){
            return "Fail to delete the post";
        }
    }


}
