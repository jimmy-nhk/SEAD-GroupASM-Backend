package com.sead.PostService.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition="text")
    private String bodyText;

    @Column
    private String category;

    @Column
    private String directors;

    @Column
    private String thumbnailURL;

    @Column
    @Builder.Default
    private long likedCount = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post" , cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<LikedUser> likedUserList = new ArrayList<>();

    @Column
    private long viewCount;
}
