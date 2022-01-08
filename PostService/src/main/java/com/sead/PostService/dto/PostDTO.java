package com.sead.PostService.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sead.PostService.model.LikedUser;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDTO {
    private Long id;
    private Long userId;
    private String title = "";
    private String bodyText = "";
    private String category = "";
    private String directors = "";
    private String coverUrl = "";
    private String tags = "";
    private long likedCount = 0;
    private List<LikedUser> likedUserList = new ArrayList<>();
    private long viewCount = 0;
    private boolean isLiked;
    private String tagline;

}
