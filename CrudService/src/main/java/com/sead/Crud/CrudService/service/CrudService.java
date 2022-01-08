package com.sead.Crud.CrudService.service;

import com.sead.Crud.CrudService.dto.*;
import com.sead.Crud.CrudService.util.model.RestPageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrudService {
    @Autowired
    private RestTemplate restTemplate;


    private final String POST_URL = "http://localhost:8082/post/";
    private final String COMMENT_URL = "http://localhost:8086/comments/";
    private final String USER_URL = "http://localhost:8080/api/";
    private final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjQwODM0MTMwLCJleHAiOjE2NDE2OTgxMzB9._ZvUfL9KuhZj_HNgUNk20zbmyV5dm4kk-a9yOQkbHtVCoywoEtREswKmHi1JZ5HyfDLHvC0jE-Q4RUUs8jvNNw";
    /**User***************************************************/
    public UserDTO getUserById(Long userId){

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(TOKEN);

        HttpEntity<UserDTO> response = restTemplate.exchange(USER_URL +"user/get/id="+userId, HttpMethod.GET, new HttpEntity<String>(headers),  UserDTO.class);
        return response.getBody();
    }


    /**User***************************************************/
    public PostDTO getPostById(Long postId){
        PostDTO postDTO = restTemplate.getForObject(POST_URL +"get/id=" + postId, PostDTO.class);
        System.out.println(postDTO.toString());
        return postDTO;
    }

    public Page<PostDTO> getALlPosts(int pageNo, int pageSize, String sortName, boolean isAsc){
        Page<PostDTO> postDTOPage = restTemplate.getForObject(POST_URL +"get/pageNo="+pageNo+"&pageSize="+pageSize+"&sortby="+sortName+"&asc="+(isAsc?"true":"false"), RestPageImpl.class);
        return postDTOPage;
    }

    public Page<PostDTO> getALlPostsByCategory(String category, int pageNo, int pageSize, String sortName){
        Page<PostDTO> postDTOPage = restTemplate.getForObject(postUrl+"get/category=" + category + "/pageNo="+pageNo+"&pageSize="+pageSize+"&sortby="+sortName, RestPageImpl.class);
        return postDTOPage;
    }

    public Page<PostDTO> getALlPostsByUserId(Long userId, int pageNo, int pageSize, String sortName){
        Page<PostDTO> postDTOPage = restTemplate.getForObject(postUrl+"get/userId=" + userId + "/pageNo="+pageNo+"&pageSize="+pageSize+"&sortby="+sortName, RestPageImpl.class);
        return postDTOPage;
    }

    public Page<PostDTO> getALlPosts(int pageNo, int pageSize, boolean isAsc){
        Page<PostDTO> postDTOPage = restTemplate.getForObject(POST_URL +"get/pageNo="+pageNo+"&pageSize="+pageSize+"&asc="+(isAsc?"true":"false"), RestPageImpl.class);
        return postDTOPage;
    }

    public PostDTO createPost(PostDTO post){
        PostDTO postDTO = restTemplate.postForObject(POST_URL +"create" , post, PostDTO.class);
        System.out.println(postDTO.toString());
        return postDTO;

    }

    // update post
    public String updatePost(PostDTO post){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PostDTO> requestUpdate = new HttpEntity<>(post, headers);

        try{
            restTemplate.exchange(POST_URL +"update" , HttpMethod.PUT, requestUpdate, PostDTO.class );

            return "Successfully update the post";
        }catch (Exception e){
            return "Fail to update";
        }
    }


    // delete post
    public String deletePost(Long postId) {

        try {
            restTemplate.delete(POST_URL +"delete/id=" + postId);

            // Delete all the comments in the post
            restTemplate.delete(COMMENT_URL +"deleteCommentsInPost/postId="+postId);
            return "Successfully delete the post";
        }catch (Exception e){
            return "Fail to delete the post";
        }
    }

    public boolean likePost(Long pid, Long uid){

//        /like/pid={pid}&uid={uid}
        Boolean likePost = restTemplate.postForObject(POST_URL +"like/pid=" + pid +"&uid=" + uid, null,Boolean.class);

        return likePost;
    }

    // delete like
    public boolean unlikePost(Long pid, Long uid) {
        try {
            restTemplate.delete(POST_URL +"like/pid=" + pid +"&uid=" + uid);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**Post***************************************************/

    /**Comment***************************************************/
    public UserCommentDTO createComment(CommentDTO commentDTO){
        CommentDTO comment = restTemplate.postForObject(COMMENT_URL +"createComment" , commentDTO, CommentDTO.class);

        UserDTO userDTO = getUserById(comment.getUserId());

        UserCommentDTO userCommentDTO = new UserCommentDTO();
        userCommentDTO.setUserDTO(userDTO);
        userCommentDTO.setCommentDTO(commentDTO);

        return userCommentDTO;
    }

    public String deleteCommentById(Long commentId){
        try {
            restTemplate.delete(COMMENT_URL +"deleteComments/commentId="+ commentId);

            return "Successfully delete the comment";
        }catch (Exception e){
            return "Fail to delete the comment";
        }
    }

    // update post
    public String updateComment( Long commentId, String body){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CommentDTO> requestUpdate = new HttpEntity<>(null, headers);

        try{
            restTemplate.put(COMMENT_URL +"/updateComment/commentId="+commentId+"/body="+ body , HttpMethod.PUT, requestUpdate, CommentDTO.class );

            return "Successfully update the comment";
        }catch (Exception e){
            return "Fail to update comment";
        }
    }


    public List<UserCommentDTO> getAllCommentsWithUserBasedOnPostId(Long postId) {

        // return the comment list
        CommentDTO[] commentList = restTemplate.getForObject(COMMENT_URL +"getAllComments/postId=" + postId, CommentDTO[].class);

        // create the userDTO
        UserDTO userDTO = null;

        // create the list to return
        List<UserCommentDTO> userCommentDTOList = new ArrayList<>();

//        HttpHeaders headers = new HttpHeaders();
//        String finalToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjQwODM0MTMwLCJleHAiOjE2NDE2OTgxMzB9._ZvUfL9KuhZj_HNgUNk20zbmyV5dm4kk-a9yOQkbHtVCoywoEtREswKmHi1JZ5HyfDLHvC0jE-Q4RUUs8jvNNw";
//        headers.setBearerAuth(finalToken);

        UserCommentDTO userCommentDTO;
        for (CommentDTO c: commentList
             ) {


            userCommentDTO = new UserCommentDTO();

            // get the user DTO
            userDTO = getUserById(c.getUserId());

            // set the object
            userCommentDTO.setCommentDTO(c);
            userCommentDTO.setUserDTO(userDTO);
            userCommentDTOList.add(userCommentDTO);
        }

        return userCommentDTOList;
    }
}
