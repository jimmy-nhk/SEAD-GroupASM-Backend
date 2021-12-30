package com.sead.Crud.CrudService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class UserDTO {
    private Long id;

    private String providerUserId;

    private String email;

    private boolean enabled;

    private String displayName;
}
