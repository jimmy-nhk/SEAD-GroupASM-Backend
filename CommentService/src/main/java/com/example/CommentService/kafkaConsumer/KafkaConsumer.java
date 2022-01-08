package com.example.CommentService.kafkaConsumer;

import com.example.CommentService.model.Comment;
import com.example.CommentService.model.CommentDTO;
import com.example.CommentService.service.CommentService;

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
    private CommentService commentService;

    private static final String COMMENT_TOPIC = "comment";

    @KafkaListener(topics = COMMENT_TOPIC, groupId = "comment_group_id_1", containerFactory = "kafkaListenerContainerFactoryObject")
    public void consume(CommentDTO commentDTO) throws IOException {
        logger.info(String.format("#### -> Consumed message: comment body-> %s", commentDTO.getBody()));

        // create comment here
        Comment comment = new Comment();
        comment.setUserId(commentDTO.getUserId());
        comment.setBody(commentDTO.getBody());
        comment.setDatePosted(commentDTO.getDatePosted());
        comment.setParentId(commentDTO.getParentId());
        comment.setPostId(commentDTO.getPostId());



        commentService.createComment(comment);

        logger.info(String.format("#### -> Date comment  message -> %s", commentDTO.getDatePosted()));

    }
}

