package com.sead.PostService.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sead.PostService.dto.PostDTOUserPOV;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long userId;

    @Column
    @Builder.Default
    private String title = "";

    @Column(columnDefinition="text")
    @Builder.Default
    private String bodyText = "";

    @Column
    @Builder.Default
    private String category = "";

    @Column
    @Builder.Default
    private String directors = "";

    @Column
    @Builder.Default
    private String thumbnailURL = "";

    @Column
    @Builder.Default
    private long likedCount = 0;

    @Column
    @Builder.Default
    private String tagline;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post" , cascade = CascadeType.ALL)
//    @JsonIgnore
    @Builder.Default
    private List<LikedUser> likedUserList = new ArrayList<>();


    @Column
    @Builder.Default
    private long viewCount = 0;


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", bodyText='" + bodyText + '\'' +
                ", category='" + category + '\'' +
                ", directors='" + directors + '\'' +
                ", thumbnailURL='" + thumbnailURL + '\'' +
                ", likedCount=" + likedCount +
                ", viewCount=" + viewCount +
                '}';
    }

    public PostDTOUserPOV toPostDTOUserPOV(Long userId){
        boolean isLiked = false;

        for (LikedUser likedUser: this.getLikedUserList()){
            if (likedUser.getUid().equals(userId)){
                isLiked = true;
                break;
            }
        }

        return PostDTOUserPOV.builder()
                .id(this.id)
                .bodyText(this.bodyText)
                .category(this.category)
                .directors(this.directors)
                .thumbnailURL(this.thumbnailURL)
                .likedCount(this.likedCount)
                .likedUserList(this.likedUserList)
                .viewCount(this.viewCount)
                .isLiked(isLiked)
                .build();
    }
}
