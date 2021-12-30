package com.sead.Crud.CrudService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentList {

    private List<CommentDTO> commentDTOList;
}
