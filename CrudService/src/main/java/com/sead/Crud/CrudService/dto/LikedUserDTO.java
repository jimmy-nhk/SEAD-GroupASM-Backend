package com.sead.Crud.CrudService.dto;


import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikedUserDTO {

    private Long id;

    private Long uid;

    private PostDTO post;

    @Override
    public String toString() {
        return "LikedUser{" +
                "id=" + id +
                ", uid=" + uid +
                ", post=" + post +
                '}';
    }
}
