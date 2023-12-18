package com.crud.entitydemo.entitySingapore.controller;


import com.crud.entitydemo.entitySingapore.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EntityController {

    @Autowired
    private EntityService entityService;


    @RequestMapping("/data")
    public void setEntityData(){
        entityService.saveEntityData();
    }

}
