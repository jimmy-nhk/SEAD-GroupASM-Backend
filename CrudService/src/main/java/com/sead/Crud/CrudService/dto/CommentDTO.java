package com.sead.Crud.CrudService.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
////@AllArgsConstructor
////@NoArgsConstructor
//@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;

    private Long parentId;

    private Long userId;

    private Long postId;

    private String datePosted;

    private String body;
}
