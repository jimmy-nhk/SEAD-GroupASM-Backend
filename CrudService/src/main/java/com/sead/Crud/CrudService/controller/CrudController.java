package com.sead.Crud.CrudService.controller;

import com.sead.Crud.CrudService.dto.PostDTO;
import com.sead.Crud.CrudService.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/crud")
public class CrudController {
    @Autowired
    CrudService crudService;

    // CRUD
        // Get Mapping
    @GetMapping(path = "/getPost/id={id}")
    public PostDTO getPostById(@PathVariable long id){
        return crudService.getPostById(id);
    }
}
