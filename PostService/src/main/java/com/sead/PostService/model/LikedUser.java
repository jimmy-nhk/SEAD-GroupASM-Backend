package com.sead.PostService.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "liked_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LikedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long uid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "pid")
    @JsonIgnoreProperties({"likedUserList"})
    private Post post;

}
