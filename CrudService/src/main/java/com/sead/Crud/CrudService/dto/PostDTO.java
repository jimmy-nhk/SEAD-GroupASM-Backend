package com.sead.Crud.CrudService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//@Data
////@AllArgsConstructor
////@NoArgsConstructor
//@Builder

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private List<LikedUserDTO> likedUserList = new ArrayList<>();
    private long viewCount = 0;
    private boolean isLiked;
    private String tagline;

}
