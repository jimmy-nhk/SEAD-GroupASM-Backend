package com.sead.Crud.CrudService.service;

import com.sead.Crud.CrudService.dto.CommentDTO;
import com.sead.Crud.CrudService.dto.PostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private static final String POST_TOPIC = "post";
    private static final String COMMENT_TOPIC = "comment";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // send post
    public boolean sendMessagePost(PostDTO postDTO){
        logger.info(String.format("#### -> Producing post message -> employee name -> %s", postDTO.getBodyText()));

        try {
            this.kafkaTemplate.send(POST_TOPIC, postDTO);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // send comment
    public boolean sendMessageComment(CommentDTO commentDTO){
        logger.info(String.format("#### -> Producing comment message -> comment date posted -> %s", commentDTO.getDatePosted()));

        try {
            this.kafkaTemplate.send(COMMENT_TOPIC, commentDTO);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
