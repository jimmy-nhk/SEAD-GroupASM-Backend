package com.sead.PostService.consumer;

import com.sead.PostService.dto.PostDTO;
import com.sead.PostService.model.Post;
import com.sead.PostService.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private PostService postService;

    private static final String POST_TOPIC = "post";
    @KafkaListener(topics = POST_TOPIC, groupId = "post_group_id_1", containerFactory = "kafkaListenerContainerFactoryObject")
    public void consume(PostDTO postDTO) throws IOException {
        logger.info(String.format("#### -> Consumed message: employee name-> %s", postDTO.getBodyText()));

        // create post here
        Post post = new Post();
        post.setUserId(postDTO.getUserId());
        post.setTitle(postDTO.getTitle());
        post.setBodyText(postDTO.getBodyText());
        post.setCategory(postDTO.getCategory());
        post.setDirectors(postDTO.getDirectors());
        post.setCoverUrl(postDTO.getCoverUrl());
        post.setTags(postDTO.getTags());
        post.setLikedCount(postDTO.getLikedCount());
        post.setViewCount(postDTO.getViewCount());
        post.setLiked(postDTO.isLiked());
        post.setTagline(postDTO.getTagline());


        postService.createPost(post);

        logger.info(String.format("#### -> Title post test message -> %s", postDTO.getTitle()));

    }
}

