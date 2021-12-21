package com.sead.PostService.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    private Long pid;

    @Column
    private String tittle;

    @Column(columnDefinition="text")
    private String bodyText;

    @Column
    private String category;

    @Column
    private String directors;

    @Column
    private String thumbnailURL;

    @Column
    private long likedCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LikedUser> likedUserList;

    @Column
    private long viewCount;
}
