package com.sead.Crud.CrudService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentDTO {

    private UserDTO userDTO;
    private CommentDTO commentDTO;
}
