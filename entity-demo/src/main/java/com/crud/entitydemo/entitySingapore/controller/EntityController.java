package com.crud.entitydemo.entitySingapore.controller;


import com.crud.entitydemo.entitySingapore.dto.request.EntityRequestDto;
import com.crud.entitydemo.entitySingapore.dto.response.EntityResponseDto;
import com.crud.entitydemo.entitySingapore.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EntityController {

    @Autowired
    private EntityService entityService;


    @Transactional
    @GetMapping("/data")
    public void setEntityData(){
        entityService.saveEntityData();
    }

    @Transactional
    @PostMapping("/entity")
    public ResponseEntity<EntityResponseDto> createEntity(@RequestBody EntityRequestDto entityRequestDto){
        return new ResponseEntity<EntityResponseDto>(entityService.createEntity(entityRequestDto), HttpStatus.CREATED);
    }



}
